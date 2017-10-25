import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Subtitles {
    public static void main(String[] args) throws IOException {

        String inputFile = "C:\\Users\\ProBook\\IdeaProjects\\JIMP3\\lab4\\zad4.4\\src\\polskie,txt";

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader= new BufferedReader(fileReader);

        try {
            String textLine = bufferedReader.readLine();

            do {
                System.out.println(textLine);
                textLine = bufferedReader.readLine();
            } while (textLine != null);
        } finally {
            bufferedReader.close();
        }
    }
}
