import interpreter.Interpreter;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        String file = args[0];

        try {
            Interpreter interpreter = new Interpreter(file);
            interpreter.Run();
        }  catch (Throwable e) {
            System.err.println(e.getMessage() + "\n\nStackTrace:");
            for (StackTraceElement el : e.getStackTrace()) {
                System.err.println("\t" + el);
            }
        }
    }
}

