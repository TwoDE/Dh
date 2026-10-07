package parser.expressions;

import interpreter.Interpreter;
import lexer.TokenType;
import objects.DhObject;
import objects.internal.DhBool;

public record BinaryExpression(Expression left, TokenType op, Expression right) implements Expression {
    public DhObject eval(Interpreter ctx) {
        DhObject l = left.eval(ctx);
        DhObject r = right.eval(ctx);

        return switch (op) {
            case PLUS  -> l.add(r);
            case MINUS -> l.sub(r);
            case STAR  -> l.mul(r);
            case SLASH -> l.div(r);
            case EQ    -> l.eq(r);
            case NEQ   -> DhBool.of(!l.eq(r).isTruth());
            case LT    -> l.lt(r);
            case GT    -> l.gt(r);
//            case LE    -> a <= b;
//            case GE    -> a >= b;
            default -> throw new RuntimeException("unknown op " + op);
        };
    }
}
