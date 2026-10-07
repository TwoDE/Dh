package parser.expressions;

import interpreter.Interpreter;
import objects.DhObject;
import objects.internal.DhInt;

public record NumberExpression(int value) implements Expression {
    public DhObject eval(Interpreter ipt) { return new DhInt(value); }
}
