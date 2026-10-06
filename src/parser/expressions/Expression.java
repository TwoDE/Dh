package parser.expressions;

import interpreter.Interpreter;

public interface Expression {
    Object eval(Interpreter ipt);
}
