package lexer;

import java.util.ArrayList;

public class Lexer {
    String text;
    int pos = 0;

    public Lexer(String text) {
        this.text = text;
    }

    public  ArrayList<Token> tokenize() {
        ArrayList<Token> tokens = new ArrayList<>();

        while (!(pos == text.length())) {
            char curr = peek();

            if (Character.isWhitespace(curr)) {
                advance();
                continue;
            }

            if (Character.isDigit(curr)) {
                StringBuilder number = new StringBuilder();

                while (!(pos == text.length()) && Character.isDigit(peek())) {
                    number.append(advance());
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString()));
                continue;
            }

            if (curr == '"') {
                advance();
                StringBuilder string = new StringBuilder();

                while (peek() != '"') {
                    string.append(advance());
                }
                advance();
                tokens.add(new Token(TokenType.STRING, string.toString()));
                continue;
            }

            if (Character.isLetter(curr) || curr == '_') {
                StringBuilder wordBuilder = new StringBuilder();

                while (!(pos == text.length()) && (Character.isLetterOrDigit(peek()) || peek() == '_')) {
                    wordBuilder.append(advance());
                }
                String word = wordBuilder.toString();

                TokenType type = switch (word) {
                    case "var" -> TokenType.VAR;
                    case "func" -> TokenType.FUNC;
                    case "print" -> TokenType.PRINT;
                    case "true" -> TokenType.BOOL_TRUE;
                    case "false" -> TokenType.BOOL_FALSE;
                    case "input" -> TokenType.INPUT;

                    default -> TokenType.ID;
                };
                tokens.add(new Token(type, word));
                continue;
            }

            if (!Character.isLetterOrDigit(curr)){
                switch (curr) {
                    case '=' -> {
                        if (peekNext() == '='){
                            tokens.add(new Token(TokenType.EQ, "=="));
                            advance();
                            break;
                        }
                        tokens.add(new Token(TokenType.ASSIGN, "="));
                    }
                    case '!' -> {
                        if (peekNext() == '='){
                            tokens.add(new Token(TokenType.NEQ, "!="));
                            advance();
                            break;
                        }
                        tokens.add(new Token(TokenType.NOT, "!"));
                    }
                    case '{' -> tokens.add(new Token(TokenType.LBRACE, "{"));
                    case '}' -> tokens.add(new Token(TokenType.RBRACE, "}"));
                    case '(' -> tokens.add(new Token(TokenType.LPAREN, "("));
                    case ')' -> tokens.add(new Token(TokenType.RPAREN, ")"));
                    case ',' -> tokens.add(new Token(TokenType.COMMA, ","));
                    case '+' -> tokens.add(new Token(TokenType.PLUS, "+"));
                    case '-' -> tokens.add(new Token(TokenType.MINUS, "-"));
                    case '*' -> tokens.add(new Token(TokenType.STAR, "*"));
                    case '/' -> tokens.add(new Token(TokenType.SLASH, "/"));
                    case ';' -> tokens.add(new Token(TokenType.SEMICOLON, ";"));
                }
            }
            advance();
        }



        tokens.add(new Token(TokenType.END, ""));
        return tokens;
    }

    private char peek() {
        return text.charAt(pos);
    }
    private char peekNext() {
        System.out.println(pos+1 + " " + text.length());
        if (pos+1 == text.length())
            return text.charAt(pos);
        return text.charAt(pos+1);
    }

    private char advance() {
        return text.charAt(pos++);
    }
}
