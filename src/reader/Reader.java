package reader;

import lexer.LexerB;
import lexer.Token;
import lexer.TokenType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Reader {

    // file and reader for him
    private final List<String> lines;
    private int currentLine = 0;

    private HashMap<String, ExecPart> parts = new HashMap<>();

    private String currentPartName;

    public Reader(String filename) {
        //create FileReader from filename with try\catch
        try {
            lines = Files.readAllLines(Paths.get(filename));

            AddPart(
                    "Main",
                    0,
                        lines.toArray().length
            );
            currentPartName = "Main";

        } catch (FileNotFoundException e) {
            System.out.println("run ex: file not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("run ex: can't read file");
            throw new RuntimeException(e);
        }

        scanFunctions();
    }

    public String NextLine() {
        ExecPart currentPart = parts.get(currentPartName);

        if (currentLine > currentPart.getStopLine()) {
            ComebackPart();
            return "";
        }
        String line = lines.get(currentLine);
        currentLine++;

        return line;
    }

    public void AddPart(String name, int start, int end) {
        parts.put(
                name,
                new ExecPart(
                        start,
                        end
                )
        );
    }

    private void ComebackPart() {
        ExecPart endedPart = parts.get(currentPartName);

        currentPartName = endedPart.getStoppedPart();

        ExecPart currentPart = parts.get(currentPartName);

        currentLine = currentPart.getStoppedLine();
    }

    public void ChangePart(String name) {
        ExecPart currentPart = parts.get(name);

        currentPart.setStoppedPart(currentPartName);

        ExecPart stoppedPart = parts.get(currentPartName);
        stoppedPart.setStoppedLine(currentLine);

        currentLine = currentPart.getStartLine();
        currentPartName = name;
    }

    private void scanFunctions() {
        String currentFuncName = null;
        int startLine = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            List<Token> tokens = LexerB.tokenizeLine(line);

            if (tokens.isEmpty()) continue;

            // Нашли объявление: func anyFunc()
            if (tokens.get(0).getType() == TokenType.KEYWORD_FUNC) {
                currentFuncName = tokens.get(1).getValue(); // "anyFunc"
            }
            // Нашли начало функции {
            else if (tokens.get(0).getType() == TokenType.LBRACE && currentFuncName != null) {
                startLine = i + 1; // Код функции начинается со след строки
            }
            // Нашли конец функции "}"
            else if (tokens.get(0).getType() == TokenType.RBRACE && currentFuncName != null) {
                // Добавляем парт автоматически!
                AddPart(currentFuncName, startLine, i - 1);
                currentFuncName = null;
            }
        }
    }

    public boolean IsEnded() {
        return (currentLine >= lines.size());
    }
    public int GetLine() { return currentLine; }

}
