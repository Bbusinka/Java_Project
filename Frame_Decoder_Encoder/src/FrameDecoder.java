import java.io.*;
import java.util.zip.CRC32;

public class FrameDecoder {
    public static int count=0;
    public static void main(String[] args) {
        String inputFile = "W.txt";
        String outputFile = "Z_decoded.txt";

        // Чтение файла с рамкой и CRC
        String input = readFile(inputFile);
        System.out.println("Tekst z ramkowaniem i CRC: " + input);

        // Проверка CRC и восстановление исходного текста
        String output = verifyCRC(input);
        System.out.println("Przywrócony tekst: " + output);

        // Запись результата в файл
        writeFile(outputFile, output);
        System.out.println("Wynik jest zapisywany do pliku: " + outputFile);

        System.out.println("Znaleziono ramek: " + count);
    }

    // Метод для чтения файла
    private static String readFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // Метод для записи файла
    private static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String verifyCRC(String input) {
        StringBuilder output = new StringBuilder(); // Декодированный результат

        int startIndex = input.indexOf("01111110"); // Индекс первой рамки

        // Пока есть найденные рамки
        while (startIndex != -1) {
            int endIndex = input.indexOf("01111110", startIndex + 1); // Индекс следующей рамки

            // Если есть следующая рамка
            if (endIndex != -1) {
                String block = input.substring(startIndex + 8, endIndex); // Блок между рамками

                // Удаление всех рамок из содержимого рамки
                String processedBlock = block.replaceAll("01111110", "");
                count++;
                // Проверка наличия данных в блоке
                if (!processedBlock.isEmpty()) {
                    // Замена последовательности "111110" на "11111"
                    processedBlock = processedBlock.replace("111110", "11111");

                    // Извлечение CRC из блока
                    String crc = processedBlock.substring(processedBlock.length() - 32);

                    // Извлечение данных из блока
                    String data = processedBlock.substring(0, processedBlock.length() - 32);

                    // Проверка CRC
                    if (verifyCRC(data, crc)) {
                        // Добавление данных блока в результирующую строку
                        output.append(data);
                    } else {
                        System.out.println("Niezgodność CRC dla bloku: " + block);
                    }
                } else {
                    System.out.println("Blok nie zawiera danych: " + block);
                }

                // Обновление индекса первой рамки
                startIndex = endIndex;
            } else {
                // Если больше нет следующей рамки, выходим из цикла
                break;
            }
        }
        return output.toString();
    }



    private static boolean verifyCRC(String data, String crc) {
        CRC32 crc32 = new CRC32();
        crc32.update(data.getBytes());
        long crcValue = crc32.getValue();
        String crc2 = String.format("%32s", Long.toBinaryString(crcValue)).replace(' ', '0');
        return crc.equals(crc2);
    }


}
