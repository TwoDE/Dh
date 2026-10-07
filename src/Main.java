import interpreter.Interpreter;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        String file = args[0];

        try {
            Interpreter interpreter = new Interpreter(file);
            interpreter.Run();
        }  catch (RuntimeException e) {
            System.err.println("\nJava Runtime Exception was occ: \n\t" + e.getMessage() + "\n\nStackTrace:");
            for (StackTraceElement el : e.getStackTrace()) {
                System.err.println("\t" + el);
            }
        } catch (Throwable e) {
            System.err.println("\nJava Throw was occ: \n" + e);
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }
}

