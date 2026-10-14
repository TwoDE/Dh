package interpreter;

import lexer.Lexer;
import lexer.LexerB;
import lexer.Token;
import parser.Parser;
import parser.statements.Statement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Interpreter {
    String text;
    private final Environment env = new Environment(); // variable's storage

    public Interpreter(String file) throws IOException {
        text = Files.readString(Path.of(file));
    }

    public void Run(){
        try {
            List<Token> tokens = new Lexer(text).tokenize();
//            System.out.println(tokens);

            List<Statement> statementList = new Parser(tokens).parse();

            for (Statement statement: statementList) {
                statement.execute(this);
            }
//                Parser parser = new Parser(tokens);
//                Statement statement = parser.parse();
//
//                statement.execute(this);
        } catch (RuntimeException e) {
            System.err.println("\nJava Runtime Exception was occurred:\n");
            System.err.print("\t" + "line" + "\n\nERROR > ");
            throw e;
        } catch (Throwable e) {
            System.err.println("\nJava Throw was occurred: \n");
            System.err.print("\t" + "line" + "\n\nERROR > ");
            throw e;
        }
    }

    public Environment getEnv() {
        return env;
    }
}
