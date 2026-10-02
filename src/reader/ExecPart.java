package reader;

public class ExecPart {
    private final int startLine;
    private final int stopLine;


    String stoppedPart;
    int stoppedLine;

    public ExecPart(
            int startLine,
            int stopLine
    ) {
        this.startLine = startLine;
        this.stopLine = stopLine;
    }

    //=======================================================================
    //=======================   getters and setters   =======================
    //=======================================================================

    public int getStartLine() {
        return startLine;
    }

    public int getStopLine() {
        return stopLine;
    }

    public String getStoppedPart() {
        return stoppedPart;
    }

    public void setStoppedPart(String stoppedPart) {
        this.stoppedPart = stoppedPart;
    }

    public int getStoppedLine() {
        return stoppedLine;
    }

    public void setStoppedLine(int stoppedLine) {
        this.stoppedLine = stoppedLine;
    }

    public String toString() {
        return "ExecPart{" +
                "startLine=" + startLine +
                ", stopLine=" + stopLine +
                ", stoppedPart='" + stoppedPart + '\'' +
                ", stoppedLine=" + stoppedLine +
                '}';
    }
}
