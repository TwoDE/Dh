package parser;

import lexer.Token;
import lexer.TokenType;
import parser.expressions.*;
import parser.statements.*;

import javax.lang.model.type.NullType;
import java.util.*;

public class Parser {
    private final List<Token> tokens;
    int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<Statement> parse() {
        ArrayList<Statement> statements = new ArrayList<>();

        while (peek().type() != TokenType.END) {
            Statement currentStmt = parseStatement();

            if (currentStmt == null) {
                throw new RuntimeException("No statements found");
            }
            advance();

            statements.add(currentStmt);
        }

        return statements;
    }

    // parse Expressions
    private Expression parseNext() {
        return parseEquality();
    }

    private Expression parseEquality() {
        Expression left = parseComparison();

        while (check(TokenType.EQ) || check(TokenType.NEQ)) {
            TokenType op = advance().type();
            Expression right = parseComparison();
            left = new BinaryExpression(left, op, right);
        }
        return left;
    }

    private Expression parseComparison() {
        Expression left = parseAdd();

        while (
                check(TokenType.LT) || check(TokenType.GT) ||
                check(TokenType.LTEQ) || check(TokenType.GTEQ)
        ) {
            TokenType op = advance().type();
            Expression right = parseAdd();
            left = new BinaryExpression(left, op, right);
        }
        return left;
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
        Expression left = parsePrimary();

        while (check(TokenType.STAR) || check(TokenType.SLASH)) {
            TokenType op = advance().type();
            Expression right = parsePrimary();
            left = new BinaryExpression(left, op, right);
        }

        return left;
    }

    private Expression parsePrimary() {
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

    public ArrayList<Statement> parseBlock() {
        ArrayList<Statement> statements = new ArrayList<>();

        expect(TokenType.LBRACE);
        while (peek().type() != TokenType.RBRACE) {
            statements.add(parseStatement());
            advance();
        }

        return statements;
    }

    public Statement parseStatement() {
        Statement currentStmt = null;

        while (peek().type() != TokenType.SEMICOLON && peek().type() != TokenType.RBRACE) {
            Token first = advance();

            switch (first.type()) {
                case PRINT -> {
                    expect(TokenType.LPAREN);
                    Statement res = new PrintStatement(parseNext());
                    expect(TokenType.RPAREN);
                    checkSemicolon();

                    currentStmt = res;
                }

                case VAR -> {
                    String name = advance().value();
                    expect(TokenType.ASSIGN);

                    currentStmt = new LetStatement(name, parseNext());
                    checkSemicolon();
                }

                case IF -> {
                    Expression expr = parseNext();

                    currentStmt = new IfStatement(
                            expr,
                            parseBlock()
                    );
                }
                case WHILE -> {
                    Expression expr = parseNext();
                    currentStmt = new WhileStatement(
                            expr,
                            parseBlock()
                    );
                }
            }
        }

        return currentStmt;
    }

    // route methods
    private Token advance() {
        return tokens.get(pos++);
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private boolean check(TokenType type) {
        return peek().type() == type;
    }

    private void expect(TokenType type) {
        if (!check(type)) {
            throw new RuntimeException("unexpected token '" + peek().type() + "'. " + type + " expect");
        }
        advance();
    }

    private void checkSemicolon(){
        if (!check(TokenType.SEMICOLON)) {
            throw new RuntimeException("; expect");
        }
//        advance();
    }
}
