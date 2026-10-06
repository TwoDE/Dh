package parser.expressions;

import interpreter.Interpreter;

public record VarExpression(String name) implements Expression {
    public Object eval(Interpreter ipt) {
        return ipt.getEnv().GetFromMem(name);
    }
}
