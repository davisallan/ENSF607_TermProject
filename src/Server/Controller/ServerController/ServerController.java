package Server.Controller.ServerController;

import java.io.*;
import java.net.Socket;

public class ServerController {

    private Socket socket;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    public ServerController(Socket socket) {
        try {
            messageIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] listenForQuery() {
        String [] query = {};
        try {
            query = messageIn.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
}
