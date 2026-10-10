package lexer;

public enum TokenType {
    // Ключевые слова
    VAR,    // var
    INPUT, // input from console

    FUNC,   // func
    PRINT,  // print

    // Идентификаторы и литералы
    ID,     // a, anyFunc
    NUMBER,         // 123
    STRING,         // "333221"

    BOOL_TRUE, BOOL_FALSE,

    // Символы и операторы
    LPAREN, RPAREN,   // ( )
    LBRACE, RBRACE,   // { }
    COMMA, // ,

    NOT,
    ASSIGN,        // =
    EQ,            // ==
    NEQ,           // !=
    LT, GT, // > <
    PLUS, MINUS, STAR, SLASH, // + - * /

    SEMICOLON,

    END
}
