package parser.statements;

import interpreter.Interpreter;
import parser.expressions.Expression;

import java.util.ArrayList;

public record IfStatement(
        Expression expr,
        ArrayList<Statement> statements
) implements Statement {
    @Override
    public void execute(Interpreter ipt) {
        if (expr.eval(ipt).isTruth()) {
            for (Statement stmt: statements) {
                stmt.execute(ipt);
            }
        }
    }
}
