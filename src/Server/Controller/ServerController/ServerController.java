package Server.Controller.ServerController;

import Server.Model.*;
import java.io.*;
import java.net.Socket;

public class ServerController {

    private Socket objectSocket;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    public ServerController(Socket socket) {
        try {
            messageIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            objectIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] listenForQuery() {
        String[] query = {};
        try {
            query = messageIn.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    public void sendObject(String objectType, ToolList list) {
        messageOut.println(objectType);
        try {
            objectOut.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
