package lexer;

public class Token {

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType type() {
        return type;
    }

    public String value() {
        return value;
    }

    public String toString(){
        return String.format("Token{%s, '%s'}", type, value);
    }
}
