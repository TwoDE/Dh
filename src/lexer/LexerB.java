package lexer;

import java.util.ArrayList;
import java.util.List;

public class LexerB {

    // Принимает ОДНУ строку и превращает в токены
    public static List<Token> tokenizeLine(String line) {
        List<Token> tokens = new ArrayList<>();
        int pos = 0;

        while (pos < line.length()) {
            char current = line.charAt(pos);

            // Пропускаем пробелы и табуляцию
            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            // Числа
            if (Character.isDigit(current)) {
                StringBuilder sb = new StringBuilder();
                while (pos < line.length() && Character.isDigit(line.charAt(pos))) {
                    sb.append(line.charAt(pos));
                    pos++;
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                continue;
            }

            // Буквы: переменные, ключевые слова (int, func, print)
            if (Character.isLetter(current) || current == '_') {
                StringBuilder sb = new StringBuilder();
                while (pos < line.length() && (Character.isLetterOrDigit(line.charAt(pos)) || line.charAt(pos) == '_')) {
                    sb.append(line.charAt(pos));
                    pos++;
                }
                String word = sb.toString();
                TokenType type = switch (word) {
                    case "int" -> TokenType.KEYWORD_INT;
                    case "func" -> TokenType.KEYWORD_FUNC;
                    case "print" -> TokenType.KEYWORD_PRINT;
                    default -> TokenType.ID;
                };
                tokens.add(new Token(type, word));
                continue;
            }

            // Строки в кавычках
            if (current == '"') {
                pos++; // пропускаем открывающую "
                StringBuilder sb = new StringBuilder();

                while (line.charAt(pos) != '"') {
                    sb.append(line.charAt(pos));
                    pos++;
                }
                pos++; // пропускаем закрывающую "
                tokens.add(new Token(TokenType.STRING, sb.toString()));
                continue;
            }

            // Одиночные символы
            switch (current) {
                case '=' -> tokens.add(new Token(TokenType.ASSIGN, "="));
                case '{' -> tokens.add(new Token(TokenType.LBRACE, "{"));
                case '}' -> tokens.add(new Token(TokenType.RBRACE, "}"));
                case '(' -> tokens.add(new Token(TokenType.LPAREN, "("));
                case ')' -> tokens.add(new Token(TokenType.RPAREN, ")"));
            }
            pos++;
        }

        tokens.add(new Token(TokenType.END, ""));
        return tokens;
    }
}