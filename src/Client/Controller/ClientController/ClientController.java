package Client.Controller.ClientController;


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
            messageOut = new PrintWriter(new OutputStreamWriter(Socket.getOutputStream()), true);
            messageIn = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
            objectOut = new ObjectOutputStream(Socket.getOutputStream());
            objectIn = new ObjectInputStream(Socket.getInputStream());
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
                messageOut.println(query); // 1

                System.out.println("\tSent query to server");
                response = messageIn.readLine(); // 2

                switch (response){
                    case "toolList":
                        try {
                            System.out.println("waiting to get new object...");
                            clientModelController.setToolList(objectIn.readObject());
                            System.out.println("received new object");
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
