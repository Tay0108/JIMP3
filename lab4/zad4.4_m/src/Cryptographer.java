import java.io.*;

public class Cryptographer {

    public static void cryptfile(String inputFile, String outputFile, Algorithm algorithm) throws IOException {

        FileReader fileReader = new FileReader(inputFile);              //tworzymy strumień odczytu znak po znaku
        BufferedReader bufferedReader = new BufferedReader(fileReader); //strumień do odczytu liniami

        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String textLine;
            String encyptedTextLine;
            while ((textLine = bufferedReader.readLine()) != null) {

                textLine = textLine.toLowerCase();
                String[] words = textLine.split("\\s"); //String[] words oraz String words[] to to samo :)
                encyptedTextLine = "";

                for (String word : words) {
                    encyptedTextLine += algorithm.crypt(word);
                    encyptedTextLine += " ";
                }

                bufferedWriter.write(encyptedTextLine);
                bufferedWriter.newLine();

            }
        } catch (java.lang.NullPointerException e) {
            System.out.println();
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    public static void decryptfile(String inputFile, String outputFile, Algorithm algorithm) throws IOException {

        FileReader fileReader = new FileReader(inputFile);              //tworzymy strumień odczytu znak po znaku
        BufferedReader bufferedReader = new BufferedReader(fileReader); //strumień do odczytu liniami

        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String textLine;
            String decryptedTextLine;
            while((textLine = bufferedReader.readLine()) != null) {

                String[] words = textLine.split("\\s");     //String[] words oraz String words[] to to samo :)
                decryptedTextLine = "";

                for (String word : words) {
                    decryptedTextLine += algorithm.decrypt(word);
                    decryptedTextLine += " ";
                }

                bufferedWriter.write(decryptedTextLine);
                bufferedWriter.newLine();
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println();
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
