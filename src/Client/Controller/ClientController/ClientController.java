package Client.Controller.ClientController;

import Client.Controller.ViewController.ClientMgmtController;
import Client.Controller.ViewController.InventoryViewController;
import Client.View.ToolShopGUI;
import CommonModel.Model.*;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private Socket Socket;
    private BufferedReader stdIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private ClientModelController clientModelController;
    private InventoryViewController inventoryViewController;
    private ClientMgmtController clientMgmtController;

    public ClientController(String serverName, int portNumber,
                            ClientModelController clientModelController) {
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

    public ClientModelController getClientModelController() {
        return clientModelController;
    }

    public void shutDown() {
        try {
            Socket.close();
            objectOut.close();
            objectIn.close();
            stdIn.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void communicate() {
        Message response;
        while (true) {
            try {
                response = (Message) objectIn.readObject();
                clientModelController.getClientShop().clearAllLists();

                switch (response.getMessage()) {
                    case "tool":
                        try {
                            Tool obj = (Tool) objectIn.readObject();
                            while (obj != null) {
                                clientModelController.getClientShop().getToolList().addTool(obj);
                                obj = (Tool) objectIn.readObject();
                            }
                            inventoryViewController.updateGUIResults(clientModelController.getClientShop().getToolList());
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
                            clientMgmtController.updateGUIResults(clientModelController.getClientShop().getCustomerList());
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

    public void sendMessage(Message message) {
        try {
            objectOut.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCustomer(Customer customer) {
        try {
            objectOut.writeObject(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Shop theShop = new Shop(new ToolInventory(new Order()), new SupplierList(), new CustomerList());
        ToolShopGUI gui = new ToolShopGUI();
        ClientModelController clientModelController = new ClientModelController(theShop);
        ClientController client = new ClientController("localhost", 8099, clientModelController);
        client.setInventoryViewController(new InventoryViewController(gui, client));
        client.setClientMgmtController(new ClientMgmtController(gui, client));
        client.communicate();
    }

    public InventoryViewController getInventoryViewController() {
        return inventoryViewController;
    }

    public void setInventoryViewController(InventoryViewController inventoryViewController) {
        this.inventoryViewController = inventoryViewController;
    }

    public ClientMgmtController getClientMgmtController() {
        return clientMgmtController;
    }

    public void setClientMgmtController(ClientMgmtController clientMgmtController) {
        this.clientMgmtController = clientMgmtController;
    }
}
