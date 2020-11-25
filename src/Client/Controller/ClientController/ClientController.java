package Client.Controller.ClientController;


import Server.Model.*;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private Socket Socket;
    private BufferedReader stdIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private ClientModelController clientModelController;

    public ClientController(String serverName, int portNumber, ClientModelController clientModelController) {
        try {
            Socket = new Socket(serverName, portNumber);
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
        Message response;
        String query = "";
        while (true) {
            try {
                clientModelController.getClientShop().clearAllLists();
                System.out.println("Enter a query in the form: {searchParameter id/name}");
                System.out.println("Where 'searchParameter' is toolId or toolName:");

                query = stdIn.readLine();
                Message msg = new Message(query);
                objectOut.writeObject(msg);

                response = (Message) objectIn.readObject();

                switch (response.getMessage()){
                    case "tool":
                        try {
                            Tool obj = (Tool) objectIn.readObject();
                            while (obj != null) {
                                clientModelController.getClientShop().getToolList().addTool(obj);
                                obj = (Tool) objectIn.readObject();
                            }
                            clientModelController.getClientShop().getToolList().display();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "customer":
                        try {
                            Customer obj = (Customer) objectIn.readObject();
                            while (obj != null) {
                                clientModelController.getClientShop().getCustomerList().addCustomer(obj);
                                obj = (Customer) objectIn.readObject();
                            }
                            clientModelController.getClientShop().getCustomerList().display();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Shop theShop = new Shop();
        ClientModelController clientModelController = new ClientModelController(theShop);
        ClientController client = new ClientController("localhost", 8099, clientModelController);
        client.communicate();
    }
}
