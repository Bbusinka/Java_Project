package sample;

public class Juja {
    public  String  boltat(){
        String s=null;
        int a = (int) (Math.random()*8);
        switch (a){
            case 0:
            {s="Мене звуть Жуужа!";
            break;}
            case 1:
            {s="Я люблю Java";
                break;}
            case 2:
            {s="Я знаю що число Р=3.14! ";
                break;}
            case 3:
            {s="мене створила Ірочка ";
                break;}

            case 4:
            {s="а ти знав що ми з Python родичі";
                break;}
            case 5:
            {s="2+2=4";
                break;}
            case 6:
            {s="правда я заслуговую 5!!!";
                break;}
            case 7:
            {s="як тебе звуть?";
                break;}
            case 8:
            {s="хі-хі-хі дуже лоскотно!";
                break;}

        }
        return s;
    }
}
