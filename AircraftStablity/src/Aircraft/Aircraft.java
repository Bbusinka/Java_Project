package Aircraft;

import java.io.Serializable;

public class Aircraft implements Serializable {
double zn1;
double zn2;
double zn3;
double rez1;
double rez2;
double rez3;


    public Aircraft(double zn1, double zn2, double zn3) {
        this.zn1 = zn1;
        this.zn2 = zn2;
        this.zn3 = zn3;
    }

    public Aircraft(double zn1, double zn2, double zn3, double rez1, double rez2, double rez3) {
        this.zn1 = zn1;
        this.zn2 = zn2;
        this.zn3 = zn3;
        this.rez1 = rez1;
        this.rez2 = rez2;
        this.rez3 = rez3;
    }



    public double getZn1() {
        return zn1;
    }

    public void setZn1(double zn1) {
        this.zn1 = zn1;
    }

    public double getZn2() {
        return zn2;
    }

    public void setZn2(double zn2) {
        this.zn2 = zn2;
    }

    public double getZn3() {
        return zn3;
    }

    public void setZn3(double zn3) {
        this.zn3 = zn3;
    }

    public double getRez1() {
        return rez1;
    }

    public void setRez1(double rez1) {
        this.rez1 = rez1;
    }

    public double getRez2() {
        return rez2;
    }

    public void setRez2(double rez2) {
        this.rez2 = rez2;
    }

    public double getRez3() {
        return rez3;
    }

    public void setRez3(double rez3) {
        this.rez3 = rez3;
    }

}
