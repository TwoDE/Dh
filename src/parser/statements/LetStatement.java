package parser.statements;

import interpreter.Interpreter;
import objects.DhObject;
import parser.expressions.Expression;

public record LetStatement(String name, Expression expr) implements Statement {
    @Override
    public void execute(Interpreter ipt) {
        DhObject v = expr.eval(ipt);

        ipt.getEnv().AddMem(name, v);
    }

    @Override
    public String toString() {
        return "LetStatement";
    }
}
