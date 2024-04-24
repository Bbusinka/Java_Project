package Aircraft;

import java.io.FileWriter;
import java.io.IOException;

public class Druk3 extends Aircraft{
    public String b;
    public Druk3(double zn1, double zn2, double zn3, double rez1, double rez2, double rez3) {
        super(zn1, zn2, zn3, rez1, rez2, rez3);

        if(rez3>0){
            b="Так як ступінь поздовжної статичної стійкості літака менше 0, то літак є нестійким.";
        }
        if(rez3<0){
            b="Так як ступінь поздовжної статичної стійкості літака менше 0, то літак є стійким.";
        }
        if(rez3==0){
            b="Так як ступінь поздовжної статичної стійкості літака менше 0, то літак є нейтральним.";
        }
        FileWriter nFile = null;
        try {
            nFile = new FileWriter("druk3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            nFile.write("\t\t\t\t Звіт з розрахуків поперечної динамічної стійкості літака \n\n" +
                    "\n Поперечна статична стійкість літака - це його здатність без участі льотчика протидіяти зміні кута нахилу."+
                    "\n\n Оскільки нам задано Уt = " +zn1+" та Lкr = " +zn3+", то відносна координата центра мас літака буде дорівнювати "+rez1+"."+
                    "\n Уf = " +zn2+" та Lкr = " +zn3+" через те, відносна координата фокуса літака за кутом ковзання буде дорівнювати "+rez2+"."+
                    "\n\n Знаючи ці дані ми знайшли ступінь поперечної статичної стійкості літака. Вона буде дорівнювати "+rez3+"."+
                    "\n\n Висновок: "+b);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            nFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }}
}

