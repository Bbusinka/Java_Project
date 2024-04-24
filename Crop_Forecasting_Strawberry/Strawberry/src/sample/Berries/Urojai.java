package sample.Berries;

public class Urojai {
    private  int rez;
    public int getRez() {
        return rez;
    }


    public Urojai(String v1, String v2, String v3, String t1, String t2) {
        if (v1.equals("80")&&v2.equals("75")&&v3.equals("70")&&t1.equals("25")&&Integer.parseInt(t2)>=15&&Integer.parseInt(t2)<=25){
            rez =100;
        }else {
            rez = (int)(50 + Math.random()*50);
        }
    }
}
