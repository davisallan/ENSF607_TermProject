package Server.Controller.ServerController;

import Server.Model.*;
import java.io.*;
import java.net.Socket;

public class ServerController {

    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    public ServerController(Socket socket) {
        try {
            messageIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            objectIn = new ObjectInputStream(socket.getInputStream());
            objectOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] listenForQuery() {
        String[] query = {};
        try {
            System.out.println("inside listenForQuery()");
            query = messageIn.readLine().split(" ");;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    public void sendMessage(String message) {
        System.out.println("inside sendMessage()");
        messageOut.println(message);
        System.out.println("\tsent message");
    }

    public void sendObject(ToolList list) {
        System.out.println("inside sendObject()");
        try {
            objectOut.writeObject(list);
            System.out.println("\tsent object");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
