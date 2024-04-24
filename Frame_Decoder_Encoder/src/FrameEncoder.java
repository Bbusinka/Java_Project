import java.io.*;
import java.util.zip.CRC32;

public class FrameEncoder {
    public static int count=0;
    public static void main(String[] args) {
        String inputFile = "Z.txt";
        String outputFile = "W.txt";

        // Чтение исходного файла
        String input = readFile(inputFile);
        System.out.println("Oryginalny tekst: " + input);

        // Вычисление и вставка CRC
        String frameWithCRC = addCRC(input);
        System.out.println("Tekst z ramkowaniem i CRC: " + frameWithCRC);

        // Запись результата в файл
        writeFile(outputFile, frameWithCRC);
        System.out.println("Wynik jest zapisywany do pliku: " + outputFile);

        System.out.println("Stworzono ramek: " + count);
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

    // Метод для добавления CRC в рамку
    private static String addCRC(String input) {
        StringBuilder frame = new StringBuilder(); // Создаем пустую рамку
        int dataLength = input.length();

        // Читаем и обрабатываем блоки по 32 символа
        for (int i = 0; i < dataLength; i += 32) {
            String block = input.substring(i, Math.min(i + 32, dataLength)); // Читаем блок из 32 символов
            String crc;
            crc = calculateCRC(block); // Вычисляем CRC для блока
            block += crc; // Добавляем CRC в блок
            block = block.replace("11111", "111110"); // Заменяем последовательность "11111" на "111110"
            frame.append("01111110"); // Открываем новую рамку
            frame.append(block); // Добавляем блок с CRC в рамку
            count++;
            // Проверяем, является ли текущий блок последним
            boolean isLastBlock = (i + 32 >= dataLength);
            if (isLastBlock) {
                frame.append("01111110"); // Добавляем закрывающую рамку в конце файла
            }
        }

        return frame.toString(); // Возвращаем сформированную рамку с CRC
    }


    // Метод для вычисления CRC
    public static String calculateCRC(String data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data.getBytes());
        long crcValue = crc32.getValue();
        String crc = String.format("%32s", Long.toBinaryString(crcValue)).replace(' ', '0');
        return crc;
    }

}
