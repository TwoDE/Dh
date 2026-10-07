package objects.internal;

import objects.DhObject;

public class DhInt extends DhObject {
    private final int value;

    public DhInt(int value) {
        this.value = value;
    }
    public int value() {
        return value;
    }

    @Override
    public String typeName() {
        return "int";
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override public DhObject add(DhObject o) {
        if (o instanceof DhInt i) return new DhInt(value + i.value);
        if (o instanceof DhString s) return new DhString(value + s.value());
        throw unsupported("+ с " + o.typeName());
    }

    @Override public DhObject sub(DhObject o) {
        if (o instanceof DhInt i) return new DhInt(value - i.value);
        throw unsupported("- с " + o.typeName());
    }

    @Override public DhObject mul(DhObject o) {
        if (o instanceof DhInt i) return new DhInt(value * i.value);
        throw unsupported("* с " + o.typeName());
    }

    @Override public DhObject div(DhObject o) {
        if (o instanceof DhInt i) {
            if (i.value == 0) throw new RuntimeException("деление на ноль");
            return new DhInt(value / i.value);
        }
        throw unsupported("/ с " + o.typeName());
    }

    @Override public DhObject eq(DhObject o) {
        if (o instanceof DhInt i) return DhBool.of(value == i.value);
        return DhBool.FALSE;
    }

    @Override public DhObject lt(DhObject o) {
        if (o instanceof DhInt i) return DhBool.of(value < i.value);
        throw unsupported("< с " + o.typeName());
    }

    @Override public DhObject gt(DhObject o) {
        if (o instanceof DhInt i) return DhBool.of(value > i.value);
        throw unsupported("> с " + o.typeName());
    }

    @Override public boolean isTruth() {
        return value != 0;
    }
}
