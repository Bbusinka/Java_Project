package sample.Users;

import java.io.FileWriter;
import java.io.IOException;

public class Druk extends User{
    public Druk(String surname, String name, String patronymic, double summ, double kom, String karta, double ost) {
        super(surname, name, patronymic);
        FileWriter nFile = null;
        try {
            nFile = new FileWriter("druk.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            nFile.write("\t\t\t\t Чек  " + "\n-------------------------------------"+
                    "\n Платник: "+ surname+" "+name+" "+patronymic+
                    "\n-------------------------------------"+
                    "\n Сума: "+summ+
                    "\n Комісія: "+kom+
                    "\n Карта для виплат: "+karta+
                    "\n-------------------------------------"+
                    "\n Залишок:  "+ost);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            nFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }}}
