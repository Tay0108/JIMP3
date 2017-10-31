import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MicroDVD testClass = new MicroDVD();
        try {
            System.out.println(System.getProperty("user.dir"));
            testClass.delay("./lab5/zad5.3_k/src/subtitles/LOTR cd1.txt", "./lab5/zad5.3_k/src/subtitles/LOTR_cd1_out.txt", 100, 25);
        } catch (InvalidFrameNumbersException e) {
            System.out.println("Invalid frame numbers exception at line " + e.getErrorLine());
        } catch(InvalidSubtitleLineException e) {
            System.out.println("Invalid subtitle line exception at line " + e.getErrorLine());
        } catch(IOException e) {
            System.out.println("IO Exception");
        }
    }
}
