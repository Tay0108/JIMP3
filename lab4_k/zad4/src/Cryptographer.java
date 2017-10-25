import java.io.*;

public class Cryptographer {

    public static void cryptfile(String inputFile, String outputFile, Algorithm algorithm) throws IOException {

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String textLine;
            String encryptedTextLine;
            String[] words;

            while ((textLine = bufferedReader.readLine()) != null) {
                textLine = textLine.toLowerCase();
                words = textLine.split("\\s");
                encryptedTextLine = "";

                for (String word : words) {
                    encryptedTextLine += algorithm.crypt(word) + " ";
                }

                bufferedWriter.write(encryptedTextLine);
                bufferedWriter.newLine();
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println();
        } finally { // wywola sie nawet jak wyrzuci wyjatek (jak nie to tez)
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    public static void decryptfile(String inputFile, String outputFile, Algorithm algorithm) throws IOException {

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String textLine;
            String decryptedTextLine;
            while (true) {
                textLine = bufferedReader.readLine();

                String[] words = textLine.split("\\s");
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

