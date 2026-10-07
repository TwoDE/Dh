package interpreter;

import objects.DhObject;

import java.util.HashMap;

public class Environment {
    private final HashMap<String, DhObject> mem = new HashMap<>();

    public void AddMem(String key, DhObject obj) {
        mem.put(key, obj);
    }

    public DhObject GetFromMem(String key) {
        if (mem.containsKey(key))
            return mem.get(key);

        throw new RuntimeException("Variable '" + key + "' is not exists");
    }
    public boolean HasKey(String key) {
        return mem.containsKey(key);
    }

}
