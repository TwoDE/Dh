package objects.internal;

import objects.DhObject;

public class DhNull extends DhObject {
    public static final DhNull INSTANCE = new DhNull();

    @Override
    public String toString() {
        return "null";
    }
    @Override
    public String typeName() {
        return "null";
    }

    @Override public DhObject eq(DhObject o) {
        return DhBool.of(o instanceof DhNull);
    }

    @Override public boolean isTruth() { return false; }
}
