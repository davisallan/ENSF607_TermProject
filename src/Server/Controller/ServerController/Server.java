package Server.Controller.ServerController;

import CommonModel.Model.*;
import Server.Controller.DBController.*;
import Server.Controller.ModelController.ModelController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private Socket client;
    private ServerSocket serverSocket;
    private ExecutorService pool;

    public Server() {
        try {
            serverSocket = new ServerSocket(8099);
            pool = Executors.newCachedThreadPool();
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
                ModelController modelController = new ModelController(new ServerController(client),
                        new DBController(new DBToolController(), new DBCustomerController(), new DBOrderController()),
                        new Shop(new ToolInventory(new Order()), new SupplierList(), new CustomerList()));
                pool.execute(modelController);
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

    public static void main(String[] args) {
        Server myServer = new Server();
        myServer.runServer();
    }
}
