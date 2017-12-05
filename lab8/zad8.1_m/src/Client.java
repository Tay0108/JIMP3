import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws IOException {

        File file = new File("./lab8/zad8.1_m/data/polish-dic.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        String pass = "";

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("szymon.ia.agh.edu.pl", 3000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));


        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: szymon.ia.agh.edu.pl.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: szymon.ia.agh.edu.pl.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;

        System.out.println("Type a message: ");
        //while ((userInput = stdIn.readLine()) != null) {
        while(line != null) {
            //System.out.println(line);
            line = br.readLine();
            out.println("LOGIN szymon;" + line);
            //System.out.println("echo: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}