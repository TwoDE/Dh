package parser.expressions;

import interpreter.Interpreter;
import objects.DhObject;

public record VarExpression(String name) implements Expression {
    public DhObject eval(Interpreter ipt) {
        return ipt.getEnv().GetFromMem(name);
    }
}
