public class MetroLine {
    private String lineName;                 // Name of metro line
    private String lineNumber;                  // Number of metro line

    public MetroLine(String lineName, String lineNumber) {
        this.lineName = lineName;
        this.lineNumber = lineNumber;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "\nLine name:\t" + getLineName() + "\tline number:\t" + getLineNumber();
    }
}
