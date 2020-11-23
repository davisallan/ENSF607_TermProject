package Server.Controller.ModelController;

import Server.Controller.DBController.DBController;
import Server.Controller.ServerController.ServerController;
import Server.Model.Shop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelController implements Runnable {

    ServerController serverController;
    DBController dbController;
    Shop theShop;
    private ResultSet rs;

    public ModelController(ServerController serverController,DBController dbController, Shop theShop) {
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
            String [] query = serverController.listenForQuery();
            options(query);
        }
    }

    private void options(String[] query) {
        switch (query[0]) {
            case "toolId": {
                rs = dbController.searchToolById(Integer.parseInt(query[1]));
                theShop.addTools(rs);
            }
            case "toolName": {

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
