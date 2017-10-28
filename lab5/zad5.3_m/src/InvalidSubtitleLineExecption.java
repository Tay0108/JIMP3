public class InvalidSubtitleLineExecption extends Exception{
    private int numberOfLine;

    public InvalidSubtitleLineExecption(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }

    public int getNumberOfLine() {
        return numberOfLine;
    }
}
