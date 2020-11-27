package Client.Controller.ClientController;

import CommonModel.Model.*;

public class ClientModelController {

    private Shop clientShop;

    public ClientModelController(Shop theShop) {
        setClientShop(theShop);
    }

    public Shop getClientShop() {
        return clientShop;
    }

    public void setClientShop(Shop clientShop) {
        this.clientShop = clientShop;
    }
}
