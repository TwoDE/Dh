package lexer;

import java.util.ArrayList;

public class Lexer {
    private final String line;
    private int pos = 0;

    public Lexer(String line) {
        this.line = line;
    }

    public ArrayList<Token> tokenize() {
        return new ArrayList<>();
    }
}
