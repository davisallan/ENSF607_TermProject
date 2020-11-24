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
            objectIn = new ObjectInputStream(socket.getInputStream());
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            messageIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] listenForQuery() {
        String[] query = {};
        try {
            System.out.println("\tinside listenForQuery()");
            query = messageIn.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    public void sendMessage(String message) {
        System.out.println("\tinside sendMessage()");
        messageOut.println(message);
        System.out.println("\t\tsent message");
    }

    public void sendObject(ToolList toolList) {
            System.out.println("\tinside sendObject()");
        try {
            for (Tool tool: toolList.getToolList()) {
                System.out.print(tool);
                objectOut.writeObject(tool);
                System.out.println("\t\tsent object");
            }
            objectOut.writeObject(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
