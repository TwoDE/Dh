package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
    private final HashMap<String, Object> mem = new HashMap<>();

    public void AddMem(String key, Object obj) {
        mem.put(key, obj);
    }
    public Object GetFromMem(String key) {
        if (mem.containsKey(key))
            return mem.get(key);

        throw new RuntimeException("Variable '" + key + "' is not exists");
    }
    public boolean HasKey(String key) {
        return mem.containsKey(key);
    }

}
