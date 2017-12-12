import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int TID = 0;

        //ThreadPool threadPool;

        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 1234");
            System.exit(-1);
        }

        try {
            while(true) { // bez ThreadPool'a
                new MyThread(serverSocket.accept(),TID); // tworzenie nowego wątku dla każdego nowego klienta
                TID++;
            }
        } catch (IOException e) {
            System.out.println("Accept failed: 1234");
            System.exit(-1);
        }

        //out.close();
        //in.close();
        clientSocket.close();
        serverSocket.close();
    }
}