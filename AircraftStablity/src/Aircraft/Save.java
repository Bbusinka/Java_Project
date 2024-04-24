package Aircraft;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Object> arr;

    public Save(ArrayList<Object> arr) {
        this.arr = arr;
    }

    public void setArr(ArrayList<Object> arr) {
        this.arr = arr;
    }

    public ArrayList<Object> getArr() { return arr; }

}