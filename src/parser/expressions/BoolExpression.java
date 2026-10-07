package parser.expressions;

import interpreter.Interpreter;
import objects.DhObject;
import objects.internal.DhBool;

public record BoolExpression(boolean value) implements Expression {
    public DhObject eval(Interpreter ipt) { return DhBool.of(value); }
}