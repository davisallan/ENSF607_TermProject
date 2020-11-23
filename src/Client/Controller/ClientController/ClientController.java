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
    private ClientModelController clientModelController;

    public ClientController(String serverName, int portNumber, ClientModelController clientModelController) {
        try {
            socket = new Socket(serverName, portNumber);
            messageIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            this.clientModelController = clientModelController;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void communicate() {
        String response = "";
        String query = "";
        while (true) {
            try {
                System.out.println("Enter a query in the form: {searchParameter id/name}");
                System.out.println("Where 'searchParameter' is toolId or toolName:");
                query = stdIn.readLine();
                messageOut.println(query);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ClientModelController clientModelController = new ClientModelController();
        ClientController client = new ClientController("localhost", 8099, clientModelController);
        client.communicate();
    }
}
