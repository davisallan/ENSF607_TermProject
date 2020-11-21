package Server.Controller.ServerController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple server multi listening for clients on port 8099
 *
 * @author Davis Allan & Santiago Flores
 * @since Nov. 20, 2020
 * @version 1.0
 */
public class Server {

    private Socket client;
    private ServerSocket serverSocket;

    private ExecutorService pool;

    /**
     * Instantiates a new Server.
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(8099);
            pool = Executors.newFixedThreadPool(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runServer() {
        try {
            while (true) {
                System.out.println("Server is running...");
                client = serverSocket.accept();
                System.out.println("Client has connected...starting new controller");

                ServerController serverController = new ServerController();
                pool.execute(serverController);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Server myServer = new Server();
        myServer.runServer();
    }
}
