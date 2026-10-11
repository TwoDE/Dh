package parser.expressions;

import interpreter.Interpreter;
import objects.DhObject;
import objects.internal.DhString;

import java.util.Scanner;

public class InputExpression implements Expression{
    private String value;

    @Override
    public DhObject eval(Interpreter ipt) {
        if (value == null) {
            value = new Scanner(System.in).nextLine();
        }
        return new DhString(value);
    }
}
