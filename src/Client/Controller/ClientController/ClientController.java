package Client.Controller.ClientController;

import Server.Model.ToolList;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private Socket Socket;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private BufferedReader stdIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private ClientModelController clientModelController;

    public ClientController(String serverName, int portNumber, ClientModelController clientModelController) {
        try {
            Socket = new Socket(serverName, portNumber);
            messageIn = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(Socket.getOutputStream()), true);
            objectIn = new ObjectInputStream(Socket.getInputStream());
            objectOut = new ObjectOutputStream(Socket.getOutputStream());
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            setClientModelController(clientModelController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClientModelController(ClientModelController clientModelController) {
        this.clientModelController = clientModelController;
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
                response = messageIn.readLine();

                switch (response){
                    case "toolList":
                        try {
                            clientModelController.setToolList(objectIn.readObject());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                }

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
