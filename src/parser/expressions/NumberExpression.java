package parser.expressions;

import interpreter.Interpreter;

public record NumberExpression(int value) implements Expression {
    public Object eval(Interpreter ipt) { return value; }
}
