package sample.Users;

import java.io.Serializable;
import java.util.HashMap;

    public class Save implements Serializable {
        private static final long serialVersionUID = 1L;
        private HashMap<String,Object> hashMap ;

        public Save(HashMap<String, Object> hashMap) {
            this.hashMap = hashMap;
        }

        public void setHashMap(HashMap<String, Object> hashMap) {
            this.hashMap = hashMap;
        }

        public HashMap<String, Object> getHashMap() {
            return hashMap;
        }
    }
