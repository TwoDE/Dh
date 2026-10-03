package interpreter;

import reader.Reader;

public class Interpreter {
    private final Reader reader;
    private Environment env = new Environment();

    States state = States.EXECUTION;

    public Interpreter(String file) {
        reader = new Reader(file);
    }

    public void Run(){
        while (!reader.IsEnded()) {
            String line = reader.NextLine();
        }
    }
}
