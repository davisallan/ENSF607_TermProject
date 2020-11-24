package Server.Controller.ModelController;

import Server.Controller.DBController.DBController;
import Server.Controller.ServerController.ServerController;
import Server.Model.Message;
import Server.Model.Shop;

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

    @Override
    public void run() {
        while (true) {
            String[] query = serverController.listenForQuery();
            String queryType = query[0];
            String condition = "";

            if (query.length > 1) {
                condition = query[1];
            }

            switch (queryType) {
                case "toolId": {
                    rs = dbController.searchToolById(Integer.parseInt(condition));
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    theShop.clearAllLists();
                    break;
                }
                case "toolName": {
                    rs = dbController.searchToolByName(condition);
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    theShop.clearAllLists();
                    break;
                }
                case "allTools": {
                    rs = dbController.selectAllTools();
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    theShop.clearAllLists();
                }
                case "checkQty": {

                }
                case "decreaseQty": {

                }
                case "customerId": {

                }
                case "customerLName": {
                    rs = dbController.searchCustomerLName(condition);
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("customer"));
                    serverController.sendObjects(theShop.getCustomerList());
                    theShop.clearAllLists();
                }
                case "customerType": {

                }
            }
        }
    }

}
