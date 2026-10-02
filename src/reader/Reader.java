package reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

    // file and reader for him
    private final List<String> lines;

    public Reader(String filename) {
        //create FileReader from filename with try\catch
        try {
            lines = Files.readAllLines(Paths.get(filename));

        } catch (FileNotFoundException e) {
            System.out.println("run ex: file not found");

            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("run ex: can't read file");

            throw new RuntimeException(e);
        }
    }

    public List<String> getLines() {
        return lines;
    }
}
