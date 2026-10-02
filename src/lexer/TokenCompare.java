package lexer;

import lexer.tokens.Token;

public record TokenCompare(
        String name,
        Token token
) {
    public boolean compareTo(String name){
        return this.name.equals(name);
    }
}
