import reader.Reader;

import java.util.Objects;

class Main {
    public static void main(String[] args) {
        String file = args[0];

        Reader reader = new Reader(file);

        ExecFile(reader);
    }

    public static void ExecFile(Reader reader) {
        while (!reader.IsEnded()) {
            String line = reader.NextLine();

            System.out.println(line);
        }
    }
}

