package sample.Users;

import java.io.Serializable;
import java.util.HashMap;


public class Work implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String,Object> hash ;

    public Work(HashMap<String, Object> hash) {
        this.hash = hash;
    }

    public void setHashMap(HashMap<String, Object> hash) {
        this.hash = hash;
    }

    public HashMap<String, Object> getHashMap() {
        return hash;
    }
}
