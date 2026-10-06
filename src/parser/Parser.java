package parser;

import interpreter.Interpreter;
import lexer.Token;
import lexer.TokenType;
import parser.expressions.*;
import parser.statements.PrintStatement;
import parser.statements.Statement;

import java.util.List;

public class Parser {
    private int pos = 0;
    private final List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Statement parse() throws Exception {
        return new PrintStatement(new Expression() {
            @Override
            public Object eval(Interpreter ipt) {
                return "Тест. принт статемент";
            }
        });
    }
}
