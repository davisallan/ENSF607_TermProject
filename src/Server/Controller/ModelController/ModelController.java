package Server.Controller.ModelController;

import Server.Controller.DBController.DBController;
import Server.Controller.ServerController.ServerController;
import Server.Model.Shop;

public class ModelController implements Runnable {

    ServerController serverController;
    DBController dbController;
    Shop theShop;

    public ModelController(ServerController serverController,DBController dbController, Shop theShop) {
        setTheShop(theShop);
        setDbController(dbController);
        setServerController(serverController);
    }

    public ServerController getServerController() {
        return serverController;
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public DBController getDbController() {
        return dbController;
    }

    public void setDbController(DBController dbController) {
        this.dbController = dbController;
    }

    public Shop getTheShop() {
        return theShop;
    }

    public void setTheShop(Shop theShop) {
        this.theShop = theShop;
    }

    @Override
    public void run() {
        while (true) {
            serverController.communicate();
        }
    }
}
