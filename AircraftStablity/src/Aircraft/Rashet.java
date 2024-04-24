package Aircraft;

import java.io.*;
import java.util.ArrayList;

public class Rashet extends Aircraft {
private ArrayList<Object> arr;
public Aircraft air;


    public Rashet(double zn1, double zn2, double zn3) {
        super(zn1, zn2, zn3);
        double a,b,c; a=zn1/zn3;
        b=zn2/zn3;c=a-b;
        air = new Aircraft(zn1, zn2, zn3, a, b, c);
        File file=new File("save.ser");
        if (file.exists()){ MyInput("save.ser");
            arr  = new ArrayList<>();arr.add(air);MyOutput(arr); }
        else { MyInput("save.ser");
            arr  = new ArrayList<>();arr.add(air);MyOutput(arr); }}
    public ArrayList<Object> MyInput(String name_file) {// повертае колекцію  для порівняння
        FileInputStream fileInputStream = null;
        try { fileInputStream = new FileInputStream("save.ser");
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        ObjectInputStream objectInputStream = null;
        try { objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) { e.printStackTrace(); }

        Save save = null;
        try { save = (Save) objectInputStream.readObject();
        } catch (IOException e) { e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); }
        ArrayList<Object> arr = save.getArr();
        return arr; }
    public void MyOutput (ArrayList<Object> arr) {
        //--------------------------------------------
        // здерігаемо данні в файл
        Save save = new Save(arr);

        //создаем 2 потока для сериализации объекта и сохранения его в файл
        FileOutputStream outputStream = null;
        try { outputStream = new FileOutputStream("save.ser");
        } catch (FileNotFoundException e) {e.printStackTrace();}
        ObjectOutputStream objectOutputStream = null;
        try {objectOutputStream = new ObjectOutputStream(outputStream);}
        catch (IOException e) { e.printStackTrace(); }

        // сохраняем  в файл
        try { objectOutputStream.writeObject(save);}
        catch (IOException e) { e.printStackTrace(); }

        //закрываем поток и освобождаем ресурсы
        try {objectOutputStream.close();
        } catch (IOException e) { e.printStackTrace(); }}}
