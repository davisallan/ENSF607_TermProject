package Client.Controller.ClientController;

import Server.Model.*;

public class ClientModelController {

    private Shop clientShop;

    public ClientModelController() {
        clientShop = new Shop();
    }

    public void setToolList(ToolList toolList) {
        clientShop.setToolList(toolList);
        System.out.println("holy fuck it worked!");

    }
}
