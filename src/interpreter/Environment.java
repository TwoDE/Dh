package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
    private HashMap<String, Object> mem = new HashMap<>();

    public void AddMem(String key, Object obj) {
        mem.put(key, obj);
    }
    public Object GetFromMem(String key) {
        return mem.get(key);
    }
    public boolean HasKey(String key) {
        return mem.containsKey(key);
    }

}
