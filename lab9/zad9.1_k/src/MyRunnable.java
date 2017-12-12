import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyRunnable implements Runnable {
    private Socket clientSocket;

    MyRunnable(Socket insocket) {
        this.clientSocket = insocket;
        this.run();
    }

    public void run() {
        while (true) {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine + " server");
                }
            } catch (IOException e) {
                System.out.println("IOException");
            }
        }
    }
}
