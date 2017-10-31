import java.io.*;
import java.util.regex.*;

public class MicroDVD {

    public void delay(final String in, final String out, int delay, int fps) throws InvalidSubtitleLineException, FileNotFoundException, InvalidFrameNumbersException, IOException {

        File inFile = new File(in);
        File outFile = new File(out);

        String line;
        Pattern textLinePattern = Pattern.compile("^\\{([0-9]+)}\\{([0-9]+)}(.*)");

        Matcher matcher;

        int startFrameNum;
        int stopFrameNum;

        // sprawdzic tu jeszcze czy sciezka in i out sa poprawne
        FileReader fileReader = new FileReader(inFile); // this is stream of characters?
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(outFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {

            String startFrame;
            String stopFrame;
            String subtitle;

            int errorLine = 1;

            while ((line = bufferedReader.readLine()) != null) {

                matcher = textLinePattern.matcher(line);
                if (matcher.find()) {
                    startFrame = matcher.group(1); // 3423
                    stopFrame = matcher.group(2); // 543543
                    subtitle = matcher.group(3); // some subtitle

                    startFrameNum = Integer.parseInt(startFrame);
                    stopFrameNum = Integer.parseInt(stopFrame);


                    if (startFrameNum > stopFrameNum) {
                        throw new InvalidFrameNumbersException(errorLine);
                    }

                    startFrameNum += (fps * delay) / 1000;
                    stopFrameNum += (fps * delay) / 1000;


                    bufferedWriter.write("{" + startFrameNum + "}{" + stopFrameNum + "}" + subtitle);
                    bufferedWriter.newLine();

                } else {
                    throw new InvalidSubtitleLineException(errorLine);
                }
                errorLine++;
            }
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
        System.out.println("Subtitles adjusted successfully.");
    }
}
