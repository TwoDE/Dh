package objects.internal;

import objects.DhObject;

public class DhBool extends DhObject {
    public static final DhBool TRUE = new DhBool(true);
    public static final DhBool FALSE = new DhBool(false);

    private final boolean value;

    public DhBool(boolean value) {
        this.value = value;
    }

    public static DhBool of(boolean b) { return b ? TRUE : FALSE; }
    public boolean value() { return value; }

    @Override public String typeName() { return "bool"; }
    @Override public String toString() { return Boolean.toString(value); }

    @Override public DhObject eq(DhObject o) {
        if (o instanceof DhBool b) return DhBool.of(value == b.value);
        return FALSE;
    }

    @Override public boolean isTruth() { return value; }
}

