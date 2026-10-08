package objects;

public abstract class DhObject {

    public abstract String typeName();
    @Override
    public abstract String toString();
//    {
//        return "DhObject with type " + type + " and value " + value;
//    }

    public DhObject add(DhObject other) {
        throw unsupported("+");
    }
    public DhObject sub(DhObject other) {
        throw unsupported("-");
    }
    public DhObject mul(DhObject other) {
        throw unsupported("*");
    }
    public DhObject div(DhObject other) {
        throw unsupported("/");
    }
    public DhObject eq(DhObject other) {
        System.out.println("ADD BOOL TYPE");
        return null;
    }

    public DhObject lt(DhObject other) {
        throw unsupported("<");
    }
    public DhObject gt(DhObject other) {
        throw unsupported(">");
    }

    public boolean isTruth() {
        return true;
    }

    protected RuntimeException unsupported(String op ) {
        return new RuntimeException(
                "Operation '" + op + "' is not supported for " + typeName()
        );
    }

}
