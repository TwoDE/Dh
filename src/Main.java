import interpreter.Interpreter;

class Main {
    public static void main(String[] args) {
        String file = args[0];

        Interpreter interpreter = new Interpreter(file);
        interpreter.Run();
    }
}

