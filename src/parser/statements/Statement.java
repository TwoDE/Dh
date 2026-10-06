package parser.statements;

import interpreter.Interpreter;

public interface Statement {
    void execute(Interpreter ipt);
}
