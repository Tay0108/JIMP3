import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 1234");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 1234");
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            out.println(inputLine + " server");
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}