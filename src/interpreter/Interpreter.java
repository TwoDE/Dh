package interpreter;

import lexer.LexerB;
import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.statements.Statement;
import reader.Reader;

import java.util.List;

public class Interpreter {
    private final Reader reader;
    private final Environment env = new Environment();

    public Interpreter(String file) {
        reader = new Reader(file);
    }

    public void Run() throws Exception {
        while (!reader.IsEnded()) {
            String line = reader.NextLine();
            if (line.isBlank())
                continue;

            List<Token> tokens = LexerB.tokenizeLine(line);
            Parser parser = new Parser(tokens);
            Statement statement = parser.parse();

            statement.execute(this);
        }
    }

    public Reader getReader() {
        return reader;
    }

    public Environment getEnv() {
        return env;
    }
}
