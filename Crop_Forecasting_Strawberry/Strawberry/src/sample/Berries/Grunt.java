package sample.Berries;

public class Grunt {
    private int v1;
    private int v2;
    private int v3;
    private int t1;
    private int t2;



    private int rezult;

    public Grunt() {
        v1 = (int)(50 + Math.random()*50);
        v2 = (int)(50 + Math.random()*50);
        v3 = (int)(50 + Math.random()*50);
        t1 = (int)(10 + Math.random()*30);
        t2 = (int)(10 + Math.random()*30);
        rezult = (int)(50 + Math.random()*50);
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public int getV3() {
        return v3;
    }

    public int getT1() {
        return t1;
    }

    public int getT2() {
        return t2;
    }
    public int getRezult() {
        return rezult;
    }
}
