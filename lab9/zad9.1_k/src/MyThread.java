import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyThread extends Thread {
    private Socket clientSocket;
    private int TID = 0;

    MyThread(Socket insocket, int TID) {
        this.TID = TID;
        this.clientSocket = insocket;
        this.start();
    }

    public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Client (" + TID + "):" + inputLine);
                    out.println("Server response:" + inputLine);
                }
            } catch (IOException e) {
                System.out.println("IOException");
            }
    }

}
