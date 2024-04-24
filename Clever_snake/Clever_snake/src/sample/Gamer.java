package sample;

import java.io.Serializable;

public class Gamer implements Serializable {
    // private String id;
    private String pass;
    private Integer reh;
    private Integer no_reh;

    public String getPass() {
        return pass;
    }

    public Integer getReh() {
        return reh;
    }

    public Integer getNo_reh() {
        return no_reh;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setReh(Integer reh) {
        this.reh = reh;
    }

    public void setNo_reh(Integer no_reh) {
        this.no_reh = no_reh;
    }


}
