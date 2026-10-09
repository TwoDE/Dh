package parser.expressions;

import interpreter.Interpreter;
import objects.DhObject;
import objects.internal.DhString;

import java.util.Scanner;

public class InputExpression implements Expression{
    private final String value = new Scanner(System.in).nextLine();

    @Override
    public DhObject eval(Interpreter ipt) {
        return new DhString(value);
    }
}
