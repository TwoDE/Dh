package parser.statements;

import interpreter.Interpreter;
import parser.expressions.Expression;

import java.util.ArrayList;

public record WhileStatement(
        Expression condition,
        ArrayList<Statement> stmtList
) implements Statement {

    @Override
    public void execute(Interpreter ipt) {
        while (condition.eval(ipt).isTruth()) {
            for (Statement stmt: stmtList) {
                stmt.execute(ipt);
                try {
                    Thread.sleep(39);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
