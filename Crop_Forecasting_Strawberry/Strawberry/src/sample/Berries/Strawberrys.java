package sample.Berries;

import java.io.Serializable;

public class Strawberrys implements Serializable {
    String vid;
    String tip;
    String v1;
    String v2;
    String v3;
    String t1;
    String t2;
    String opl;
    String rez;
    public String getVid() {
        return vid;
    }

    public String getTip() {
        return tip;
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    public String getV3() {
        return v3;
    }

    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public String getOpl() {
        return opl;
    }

    public String getRez() {
        return rez;
    }



    public Strawberrys(String vid, String tip, String v1, String v2, String v3, String t1, String t2, String opl, String rez) {
        this.vid = vid;
        this.tip = tip;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.t1 = t1;
        this.t2 = t2;
        this.opl = opl;
        this.rez = rez;
    }
}
