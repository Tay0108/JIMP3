public class InvalidSubtitleLineException extends Exception{
    private int numberOfLine;

    public InvalidSubtitleLineException(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }

    public int getNumberOfLine() {
        return numberOfLine;
    }
}
