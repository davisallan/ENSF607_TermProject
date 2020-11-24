package Server.Controller.ModelController;

import Server.Controller.DBController.DBController;
import Server.Controller.ServerController.ServerController;
import Server.Model.Message;
import Server.Model.Shop;

import java.sql.ResultSet;
import java.util.Arrays;

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
            System.out.println(Arrays.toString(query));
            switch (query[0]) {
                case "toolId": {
                    System.out.println("toolid");
                    rs = dbController.searchToolById(Integer.parseInt(query[1]));
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("toolList"));
                    serverController.sendObject(theShop.getToolList());
                    theShop.clearAllLists();
                    break;
                }
                case "toolName": {
                    System.out.println("case toolna");
                    rs = dbController.searchToolByName(query[1]);
                    theShop.addTools(rs);
                    serverController.sendMessage(new Message("toolList"));
                    serverController.sendObject(theShop.getToolList());
                    theShop.clearAllLists();
                    break;
                }
                case "allTools": {

                }
                case "checkQty": {

                }
                case "decreaseQty": {

                }
                case "customerId": {

                }
                case "customerLName": {

                }
                case "customerType": {

                }
            }
        }
    }

}
