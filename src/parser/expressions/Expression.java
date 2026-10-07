package parser.expressions;

import interpreter.Interpreter;
import objects.DhObject;

public interface Expression {
    DhObject eval(Interpreter ipt);
}
