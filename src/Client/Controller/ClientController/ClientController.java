package Client.Controller.ClientController;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private Socket socket;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private BufferedReader stdIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    public ClientController(String serverName, int portNumber) {
        try {
            socket = new Socket(serverName, portNumber);
            messageIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void communicate() {
        String response = "";
        while (true) {
            messageOut.println("meow");
            try {
                response = messageIn.readLine();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ClientController client = new ClientController("localhost", 8099);
        client.communicate();
    }
}
