package parser.expressions;

import interpreter.Interpreter;
import objects.DhObject;
import objects.internal.DhString;

public record StringExpression(String value) implements Expression {
    public DhObject eval(Interpreter ipt) { return new DhString(value); }
}