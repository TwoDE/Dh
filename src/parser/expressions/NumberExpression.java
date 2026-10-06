package parser.expressions;

import interpreter.Interpreter;

public record NumberExpression(double value) implements Expression {
    public Object eval(Interpreter ipt) { return value; }
}
