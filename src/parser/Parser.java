package parser;

import interpreter.Interpreter;
import lexer.Token;
import lexer.TokenType;
import parser.expressions.*;
import parser.statements.LetStatement;
import parser.statements.PrintStatement;
import parser.statements.Statement;

import javax.naming.ldap.ExtendedRequest;
import java.util.List;

public class Parser {
    private int pos = 0;
    private final List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Statement parse() throws Exception {

        System.out.println(tokens);
        Token first = peek();
        switch (first.type()) {
            case TokenType.KEYWORD_PRINT -> {
                return new PrintStatement(nextExpression());
            }
            case TokenType.KEYWORD_VAR -> {
                String name = advance().value();

                Token next = advance();

                if (next.type() != TokenType.ASSIGN)
                    throw new RuntimeException("unexpected token '" + next+"'");

                return new LetStatement(name, nextExpression());
            }
        }
        return new PrintStatement(new StringExpression("но-статемент"));
    }

    private Expression nextExpression() {
        Token exprToken = advance();
        switch (exprToken.type()) {
            case STRING -> {
                return new StringExpression(exprToken.value());
            }
            case NUMBER -> {
                return new NumberExpression(Integer.parseInt(exprToken.value()));
            }
            case ID -> {
                return new VarExpression(exprToken.value());
            }
        }
        throw new RuntimeException("Can't parse expression from " + exprToken);
    }

    private Token peek() {
        return tokens.get(pos);
    }
    private Token advance() {
        return tokens.get(++pos);
    }


}
