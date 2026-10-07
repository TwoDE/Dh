package objects.internal;

import objects.DhObject;

public class DhString extends DhObject {
    private final String value;

    public DhString (String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override public String toString() {
        return value;
    }
    public String typeName() {
        return "string";
    }

    @Override public DhObject eq(DhObject o) {
        if (o instanceof DhString s) return DhBool.of(value.equals(s.value));
        return DhBool.FALSE;
    }

    @Override public boolean isTruth() {
        return !value.isEmpty();
    }

}
