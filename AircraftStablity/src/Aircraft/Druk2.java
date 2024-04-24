package Aircraft;

import java.io.FileWriter;
import java.io.IOException;

public class Druk2 extends Aircraft{
    public String b;
    public Druk2(double zn1, double zn2, double zn3, double rez1, double rez2, double rez3) {
        super(zn1, zn2, zn3, rez1, rez2, rez3);

        if(rez3>0){
            b="Так як ступінь шляхової статичної стійкості літака менше 0, то літак є нестійким.";
        }
        if(rez3<0){
            b="Так як ступінь шляхової статичної стійкості літака менше 0, то літак є стійким.";
        }
        if(rez3==0){
            b="Так як ступінь шляхової статичної стійкості літака менше 0, то літак є нейтральним.";
        }
        FileWriter nFile = null;
        try {
            nFile = new FileWriter("druk2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            nFile.write("\t\t\t\t Звіт з розрахуків шляхової статичної стійкості літака \n\n" +
                    "\n Шляхова статична стійкість літака - це його здатність без участі льотчика протидіяти зміні кута ковзання."+
                    "\n\n Оскільки нам задано Xt = " +zn1+" та Lкr = " +zn3+", то відносна координата центра мас літака буде дорівнювати "+rez1+"."+
                    "\n Xf = " +zn2+" та Lкr = " +zn3+" через те, відносна координата фокуса літака за кутом ковзання буде дорівнювати "+rez2+"."+
                    "\n\n Знаючи ці дані ми знайшли ступінь шляхової статичної стійкості літака. Вона буде дорівнювати "+rez3+"."+
                    "\n\n Висновок: "+b);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            nFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }}



}

