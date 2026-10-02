import reader.Reader;

class Main {
    public static void main(String[] args) {
        String file = "./src/main.dh4";

        Reader reader = new Reader(file);

        ExecFile(reader);
    }

    public static void ExecFile(Reader reader) {
        for (String line : reader.getLines()) {
            System.out.println(line);
        }
    }
}

