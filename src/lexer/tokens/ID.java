package lexer.tokens;

public class ID implements Token{
    private final String id;
    private final Object value;

    public ID(String id, Object value) {
        this.id = id;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getId() {
        return id;
    }
}
