package lexer;

public enum TokenType {
    // Ключевые слова
    KEYWORD_VAR,    // int
    KEYWORD_FUNC,   // func
    KEYWORD_PRINT,  // print

    // Идентификаторы и литералы
    ID,     // a, anyFunc
    NUMBER,         // 123
    STRING,         // "333221"

    // Символы и операторы
    LPAREN, RPAREN,   // ( )
    LBRACE, RBRACE,   // { }
    COMMA,

    ASSIGN,        // =
    EQ,            // ==
    NEQ,           // !=
    LT, GT, LE, GE,
    PLUS, MINUS, STAR, SLASH,

    END
}
