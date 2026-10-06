package parser.statements;

import interpreter.Interpreter;
import parser.expressions.Expression;

public record PrintStatement(Expression expr) implements Statement {
    @Override
    public void execute(Interpreter ipt) {
        Object v = expr.eval(ipt);
        System.out.println(v);
    }

    @Override
    public String toString() {
        return "PrintStatement";
    }
}
