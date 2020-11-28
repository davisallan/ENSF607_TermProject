package Server.Controller.ServerController;

import CommonModel.Model.*;

import java.io.*;
import java.net.Socket;

public class ServerController {

    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    public ServerController(Socket socket) {
        try {
            objectIn = new ObjectInputStream(socket.getInputStream());
            objectOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getObjectIn() {
        return objectIn;
    }

    public ObjectOutputStream getObjectOut() {
        return objectOut;
    }

    public String[] listenForQuery() {
        String[] query = {};
        try {
            Message msg = (Message) objectIn.readObject();
            query = msg.getMessage().split("-");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return query;
    }

    public Object listenForObject() {
        Object obj = null;
        try {
            obj = objectIn.readObject();
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
        return obj;
    }

    public void sendMessage(Message message) {
        try {
            objectOut.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObjects(ToolInventory toolInventory) {
//        System.out.println(toolList.getToolList().get(0));
        try {
            for (Tool tool : toolInventory.getToolList()) {
                objectOut.writeObject(tool);
            }
            objectOut.writeObject(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObjects(CustomerList customerList) {
//        System.out.println(customerList.getCustomerList().get(0));
        try {
            for (Customer customer : customerList.getCustomers()) {
                objectOut.writeObject(customer);
            }
            objectOut.writeObject(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
