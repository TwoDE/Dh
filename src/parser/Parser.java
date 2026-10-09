package parser;

import lexer.Token;
import lexer.TokenType;
import parser.expressions.*;
import parser.statements.LetStatement;
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

        System.out.println(tokens);
        Token first = advance();
        switch (first.type()) {
            case TokenType.PRINT -> {
                return new PrintStatement(parseNext());
            }

            case TokenType.VAR -> {
                String name = advance().value();

                expect(TokenType.ASSIGN);

                return new LetStatement(name, parseNext());
            }

        }
        throw new RuntimeException("no statements found: " + tokens);
    }

    private Expression parseNext() {
        return parseAdd();
    }

    private Expression parseAdd() {
        Expression left = parseMul();

        while (check(TokenType.PLUS) || check(TokenType.MINUS)) {
            TokenType op = advance().type();
            Expression right = parseMul();
            left = new BinaryExpression(left, op, right);
        }

        return left;
    }

    private Expression parseMul() {
        Expression left = nextPrimary();

        while (check(TokenType.STAR) || check(TokenType.SLASH)) {
            TokenType op = advance().type();
            Expression right = nextPrimary();
            left = new BinaryExpression(left, op, right);
        }

        return left;
    }

    private Expression nextPrimary() {
        Token exprToken = advance();
        switch (exprToken.type()) {
            case STRING -> {
                return new StringExpression(exprToken.value());
            }
            case NUMBER -> {
                return new NumberExpression(Integer.parseInt(exprToken.value()));
            }
            case TokenType.BOOL_FALSE, TokenType.BOOL_TRUE -> {
                return new BoolExpression(Boolean.parseBoolean(exprToken.value()));
            }
            case INPUT -> {
                return new InputExpression();
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

    private boolean check(TokenType type) {
        return peek().type() == type;
    }

    private Token advance() {
        return tokens.get(pos++);
    }


    private Token expect(TokenType token) {
        if (!check(token)) {
            throw new RuntimeException("unexpected token '" + peek().type() + "'");
        }
        return advance();
    }


}
