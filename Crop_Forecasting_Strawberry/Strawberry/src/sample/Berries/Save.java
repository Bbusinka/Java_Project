package sample.Berries;

import java.io.Serializable;
import java.util.HashMap;

public class Save implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<Integer,Object> hashMap ;

    public Save(HashMap<Integer, Object> hashMap) {
        this.hashMap = hashMap;
    }

    public void setHashMap(HashMap<Integer, Object> hashMap) {
        this.hashMap = hashMap;
    }

    public HashMap<Integer, Object> getHashMap() {
        return hashMap;
    }

}
