import java.io.*;

public class Subtitles {
    public static void main(String[] args) throws IOException {

        String inputFile = args[0];
        String outputFile = args[1];
        int delay = Integer.parseInt(args[2]);
        int framerate = Integer.parseInt(args[3]);

        MicroDvd microDvd = new MicroDvd();

        try {
            microDvd.delay(inputFile, outputFile, delay, framerate);
        } catch (InvalidSubtitleLineException e) {
            System.out.println("Niepoprawny format w lini:" + e.getNumberOfLine());
        } catch (InvalidFrameNumbersExecption e) {
            System.out.println("Niepoprawne oznaczenie klatek w linii: " + e.getNumberOfLine());
        }
    }
}
