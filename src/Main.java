import interpreter.Interpreter;

class Main {
    public static void main(String[] args) {
        String file = args[0];

        try {
            Interpreter interpreter = new Interpreter(file);
            interpreter.Run();
        }  catch (RuntimeException e) {
            System.err.println("\nJava Runtime Exception was occ: \n" + e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            System.err.println("\nJava Throw was occ: \n" + e.toString());
            e.printStackTrace();
        }
    }
}

