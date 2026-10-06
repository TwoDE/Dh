package parser;

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
        if (match(TokenType.KEYWORD_PRINT)) {
            Expression e = parseExpr();
            return new PrintStatement(e);
        }
        throw new Exception("неожиданный токен " + peek());
    }

    private Expression parseExpr() throws Exception {
        return parseEquality();
    }

    private Expression parseEquality() throws Exception {
        Expression left = parseComparison();

        while (check(TokenType.EQ) || check(TokenType.NEQ)) {
            TokenType op = advance().type();
            Expression right = parseComparison();
            left = new BinaryExpression(left, op, right);
        }
        return left;
    }

    private Expression parseComparison() throws Exception {
        Expression left = parseAdditive();
        while (check(TokenType.LT) || check(TokenType.GT)
                || check(TokenType.LE) || check(TokenType.GE)) {
            TokenType op = advance().type();
            Expression right = parseAdditive();
            left = new BinaryExpression(left, op, right);
        }
        return left;
    }
    // +, -
    private Expression parseAdditive() throws Exception {
        Expression left = parseMultiplicative();
        while (check(TokenType.PLUS) || check(TokenType.MINUS)) {
            TokenType op = advance().type();
            Expression right = parseMultiplicative();
            left = new BinaryExpression(left, op, right);
        }
        return left;
    }

    // *, /
    private Expression parseMultiplicative() throws Exception {
        System.out.println(pos);
        Expression left = parsePrimary();
        System.out.println(pos);
        while (check(TokenType.STAR) || check(TokenType.SLASH)) {
            TokenType op = advance().type();
            Expression right = parsePrimary();
            left = new BinaryExpression(left, op, right);
        }
        return left;
    }

    // числа, строки, переменные, (expr)
    private Expression parsePrimary() throws Exception {
        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(tokens.get(pos - 1).value()));
        }
        if (match(TokenType.STRING)) {
            return new StringExpression(tokens.get(pos - 1).value());
        }
        if (match(TokenType.ID)) {
            return new VarExpression(tokens.get(pos - 1).value());
        }
        if (match(TokenType.LPAREN)) {
            Expression e = parseExpr();
            expect(TokenType.RPAREN, ")");
            return e;
        }
        throw new Exception("ожидалось выражение, получено " + peek());
    }

    //==== утилиты ====

    private Token peek() {
        return tokens.get(pos);
    }

    private Token peek(int offset) {
        int i = pos + offset;
        return i < tokens.size() ? tokens.get(i) : tokens.getLast();
    }

    private Token advance() {
        return tokens.get(pos++);
    }

    private boolean check(TokenType t) {
        return peek().type() == t;
    }

    private boolean match(TokenType t) {
        if (check(t)) { advance(); return true; }
        return false;
    }

    private Token expect(TokenType t, String what) throws Exception {
        if (!check(t)) {
            throw new Exception(
                    "ожидалось " + what + ", а получено " + peek() + " на строке "
            );
        }
        return advance();
    }

}
