package Server.Controller.ModelController;

import Server.Controller.DBController.DBController;
import Server.Controller.ServerController.ServerController;
import Server.Model.Message;
import Server.Model.Shop;

import java.io.IOException;
import java.sql.ResultSet;

public class ModelController implements Runnable {

    ServerController serverController;
    DBController dbController;
    Shop theShop;
    private ResultSet rs;

    public ModelController(ServerController serverController, DBController dbController, Shop theShop) {
        setTheShop(theShop);
        setDbController(dbController);
        setServerController(serverController);
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public void setDbController(DBController dbController) {
        this.dbController = dbController;
    }

    public void setTheShop(Shop theShop) {
        this.theShop = theShop;
    }

    public void closeConnections() {
        try {
            dbController.close();
            serverController.getObjectIn().close();
            serverController.getObjectOut().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            String[] query = serverController.listenForQuery();
            String queryType = query[0]; //the type of requested query
            String condition = "";

            if (queryType.equals("quit")) {
                serverController.sendMessage(new Message("quit"));
                System.out.println("Client has disconnected...");
                break;
            }

            if (query.length > 1) {
                condition = query[1]; //the condition of the requested query
            }

            switch (queryType) {
                case "toolId": {
                    rs = dbController.searchToolById(Integer.parseInt(condition));
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "toolName": {
                    rs = dbController.searchToolByName(condition);
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "allTools": {
                    rs = dbController.selectAllTools();
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "customerId": {
                    rs = dbController.searchCustomerByID(Integer.parseInt(condition));
                    theShop.addCustomers(rs);
                    serverController.sendMessage(new Message("customer"));
                    serverController.sendObjects(theShop.getCustomerList());
                    break;
                }
                case "customerLName": {
                    rs = dbController.searchCustomerByLName(condition);
                    theShop.addCustomers(rs);
                    serverController.sendMessage(new Message("customer"));
                    serverController.sendObjects(theShop.getCustomerList());
                    break;
                }
                case "customerType": {
                    rs = dbController.searchByCustomerType(condition);
                    theShop.addCustomers(rs);
                    serverController.sendMessage(new Message("customer"));
                    serverController.sendObjects(theShop.getCustomerList());
                    break;
                }
                case "updateCustomer": {

                    break;
                }
                case "sell": {
                    rs = dbController.selectAllTools();
                    theShop.addTools(rs);
                    theShop.sellItem(Integer.parseInt(condition));
                    dbController.sellItem(Integer.parseInt(condition));
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
//                case "deleteCustomer": {
//
//                    break;
//                }
//
//                case "checkQty": {
//
//                    break;
//                }
//                case "decreaseQty": {
//
//                    break;
//                }
            }
            theShop.clearAllLists();
        }
        closeConnections();
    }
}


