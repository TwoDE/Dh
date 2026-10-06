package parser.expressions;

import interpreter.Interpreter;
import lexer.TokenType;

public record BinaryExpression(Expression left, TokenType op, Expression right) implements Expression {
    public Object eval(Interpreter ctx) {
        Object l = left.eval(ctx);
        Object r = right.eval(ctx);

        // упрощённо: только числа
        double a = ((Number) l).doubleValue();
        double b = ((Number) r).doubleValue();

        return switch (op) {
            case PLUS  -> a + b;
            case MINUS -> a - b;
            case STAR  -> a * b;
            case SLASH -> a / b;
            case EQ    -> a == b;
            case NEQ   -> a != b;
            case LT    -> a < b;
            case GT    -> a > b;
            case LE    -> a <= b;
            case GE    -> a >= b;
            default -> throw new RuntimeException("unknown op " + op);
        };
    }
}
