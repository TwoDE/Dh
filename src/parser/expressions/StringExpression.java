package parser.expressions;

import interpreter.Interpreter;

public record StringExpression(String value) implements Expression {
    public Object eval(Interpreter ipt) { return value; }
}