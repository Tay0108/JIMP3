public class InvalidFrameNumbersExecption extends Exception {
    private int numberOfLine;

    public InvalidFrameNumbersExecption(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }

    public int getNumberOfLine() {
        return numberOfLine;
    }
}
