package sample;

public class User {
    private String id;
    private int reh;
    private int no_reh;


    public User(String id, int reh, int no_reh) {
        this.id = id;
        this.reh = reh;
        this.no_reh = no_reh;

    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getReh() {
        return reh;
    }

    public void setReh(int reh) {
        this.reh = reh;
    }
    public int getNo_reh() {
        return no_reh;
    }

    public void setNo_reh(int no_reh) {
        this.no_reh = no_reh;
    }
}

