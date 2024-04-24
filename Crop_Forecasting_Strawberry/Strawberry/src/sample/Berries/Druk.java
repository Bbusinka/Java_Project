package sample.Berries;

import java.io.FileWriter;
import java.io.IOException;

public class Druk {

    public Druk(String id, String text, String text1, String text2, String text3, String text4, String text5,
                String text6, String text7, String text8) {
        FileWriter nFile = null;
        try {
            nFile = new FileWriter("druk.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            nFile.write(" Код тесту  "+id+"" +
                    "\n Тип вирощування "+text+
                    "\n Спосіб вирощування "+text1+
                    "\n Перші 3 тижні після посадки "+text2+
                    "\n До цвітіння "+text3+
                    "\n В період цвітіння  "+text4+
                    "\n З моменту висадки до укореніння  "+text5+
                    "\n До врожайності  "+text6+
                    "\n Метод опилення  "+text7+
                    "\n Потенційний коефіцієнт врожайності "+text8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            nFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }

