package lexer;

public enum TokenType {
    // Ключевые слова
    KEYWORD_INT,    // int
    KEYWORD_FUNC,   // func
    KEYWORD_PRINT,  // print

    // Идентификаторы и литералы
    IDENTIFIER,     // a, anyFunc
    NUMBER,         // 123
    STRING,         // "333221"

    // Символы и операторы
    ASSIGN,         // =
    LPAREN,         // (
    RPAREN,         // )
    LBRACE,         // {
    RBRACE,         // }

    // Служебные
    NEWLINE,        // Конец строки (важно для разделения команд)
    EOF             // Конец файла (End Of File)
}
