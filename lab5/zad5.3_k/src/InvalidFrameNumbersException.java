public class InvalidFrameNumbersException extends Exception {
    private int errorLine;

    InvalidFrameNumbersException(int errorLine) {
        this.errorLine = errorLine;
    }

    public int getErrorLine() {
        return errorLine;
    }
}
