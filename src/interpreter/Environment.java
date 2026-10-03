package interpreter;

import objects.DhObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
    private HashMap<String, DhObject> mem = new HashMap<>();

    public void AddMem(String key, DhObject obj) {
        mem.put(key, obj);
    }
    public DhObject GetFromMem(String key) {
        return mem.get(key);
    }

}
