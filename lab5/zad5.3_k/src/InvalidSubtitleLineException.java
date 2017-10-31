public class InvalidSubtitleLineException extends Exception {
    private int errorLine;

    InvalidSubtitleLineException(int errorLine) {
        this.errorLine = errorLine;
    }

    public int getErrorLine() {
        return errorLine;
    }
}
