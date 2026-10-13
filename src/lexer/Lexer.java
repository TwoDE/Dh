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

            if (Character.isWhitespace(curr)) { // skip spaces
                advance();
                continue;
            }

            if (curr == '#') {
                do {
                    advance();
                } while (peek()!='\n');
            }

            if (Character.isDigit(curr)) { // check for NUMBER token
                StringBuilder number = new StringBuilder();

                while (!(pos == text.length()) && Character.isDigit(peek())) {
                    number.append(advance());
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString()));
                continue;
            }

            if (curr == '"') { // check for STRING token
                advance();
                StringBuilder string = new StringBuilder();

                while (peek() != '"') {
                    string.append(advance());
                }
                advance();
                tokens.add(new Token(TokenType.STRING, string.toString()));
                continue;
            }

            if (Character.isLetter(curr) || curr == '_') { // check keywords and IDs
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
                    case "if" ->  TokenType.IF;

                    default -> TokenType.ID;
                };
                tokens.add(new Token(type, word));
                continue;
            }

            if (!Character.isLetterOrDigit(curr)){ // check another tokens: =, ==, !, !=, ;....
                switch (curr) {
                    case '=' -> {
                        if (peekNext() == '='){ // search for "=="
                            tokens.add(new Token(TokenType.EQ, "=="));
                            advance();
                            break;
                        }
                        tokens.add(new Token(TokenType.ASSIGN, "=")); // if it only "="
                    }
                    case '!' -> {
                        if (peekNext() == '='){ // search for "!="
                            tokens.add(new Token(TokenType.NEQ, "!="));
                            advance();
                            break;
                        }
                        tokens.add(new Token(TokenType.NOT, "!")); // if it only "!"
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



        tokens.add(new Token(TokenType.END, "")); // end of program
        return tokens;
    }

    private char peek() { // get current token
        return text.charAt(pos);
    }
    private char peekNext() { // try to get next token
        if (pos+1 == text.length()) // check the file end
            return text.charAt(pos);
        return text.charAt(pos+1); // if it's end of file
    }

    private char advance() { // get current token and offset pos
        return text.charAt(pos++);
    }
}
