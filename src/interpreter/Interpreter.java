package interpreter;

import lexer.LexerB;
import lexer.Token;
import lexer.TokenType;
import reader.Reader;

import java.util.List;

public class Interpreter {
    private final Reader reader;
    private Environment env = new Environment();

    States state = States.EXECUTION;

    public Interpreter(String file) {
        reader = new Reader(file);
    }

    public void Run(){
        boolean insideFuncDec = false;

        while (!reader.IsEnded()) {
            String line = reader.NextLine();
            if (line.isBlank())
                continue;
            List<Token> tokens = LexerB.tokenizeLine(line);

            if (tokens.get(0).getType() == TokenType.KEYWORD_FUNC) {
                insideFuncDec = true;
                continue;
            }

            // 2. Если внутри объявления функции встретили '}', значит функция закончилась
            if (insideFuncDec) {
                if (tokens.get(0).getType() == TokenType.RBRACE) {
                    insideFuncDec = false;
                }
                continue; // Пропускаем строки тела функции во время первоначального чтения!
            }

            executeLine(tokens, reader);
        }
    }

    private void executeLine(List<Token> tokens, Reader reader) {
        TokenType firstTokenType = tokens.get(0).getType();

        // Переменные: int a = 12
        if (firstTokenType == TokenType.KEYWORD_INT) {
            String varName = tokens.get(1).getValue();
            String varValue = tokens.get(3).getValue();
            env.AddMem(varName, 123);
            System.out.println("[LOG] Записали переменную: " + varName + " = " + varValue);
        }

        // Печать: print "333221"
        else if (firstTokenType == TokenType.KEYWORD_PRINT) {
            String val = tokens.get(1).getValue();
            // Проверяем, это переменная или строка
            System.out.println(val);
        }

        // Вызов функции или переменная: anyFunc()
        else if (firstTokenType == TokenType.IDENTIFIER) {
            String name = tokens.get(0).getValue();

            // Если после имени идет '(', значит это вызов функции!
            if (tokens.size() > 1 && tokens.get(1).getType() == TokenType.LPAREN) {
                // Переключаем парт у ридера!
                reader.ChangePart(name);
            }
        }
    }
}
