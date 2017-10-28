import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicroDvd {

    public void delay(final String in, final String out, int delay, int fps)
            throws IOException, InvalidSubtitleLineExecption, InvalidFrameNumbersExecption {

        FileReader fileReader = new FileReader(in);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(out);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int numberOfLine = 0;
        Pattern pattern = Pattern.compile("\\{([0-9]+)}\\{([0-9]+)}(.*)");
        /*
        [0-9]+ - jedno lub więcej wystąpień znaków z zakresu 0-9
        .*     - zero lub więcej wystąpień dowolnego znaku
        (...)  - grupy (do matchera)
         */
        Matcher matcher;
        int startFrame, endFrame;

        try {
            String textLineIn;
            String textLineOut;

            while ((textLineIn = bufferedReader.readLine()) != null) {

                numberOfLine++;
                matcher = pattern.matcher(textLineIn);
                /*
                group(0) - cała linia, group(n) - n-ta grupa
                 */
                if (matcher.find()) {
                    startFrame = Integer.parseInt(matcher.group(1));
                    endFrame = Integer.parseInt(matcher.group(2));

                    if (startFrame > endFrame) {
                        throw new InvalidFrameNumbersExecption(numberOfLine);
                    }

                    startFrame += (fps*delay)/1000;
                    endFrame += (fps*delay)/1000;

                    textLineOut = "{" + startFrame + "}{" + endFrame + "}" + matcher.group(3) + "\n";
                    bufferedWriter.write(textLineOut);
                } else {
                    throw new InvalidSubtitleLineExecption(numberOfLine);
                }
            }
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
