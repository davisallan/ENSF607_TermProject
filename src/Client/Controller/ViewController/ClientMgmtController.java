package Client.Controller.ViewController;

import Client.Controller.ClientController.ClientController;
import Client.View.ToolShopGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMgmtController {

    private ToolShopGUI gui;
    private CardLayout cardLayout;
    private ClientController clientController;

    public ClientMgmtController(ToolShopGUI gui, ClientController clientController) {
        setGui(gui);
        setClientController(clientController);
        cardLayout = (CardLayout) gui.getRootPanel().getLayout();
        gui.addButtonActionListener(gui.getGoToToolsButton(), new ClientMgmtController.ToolCardListener());
        gui.addButtonActionListener(gui.getClearButton1(), new ClientMgmtController.ClearButton1());
        gui.addButtonActionListener(gui.getClearButton(), new ClientMgmtController.ClearButton());
    }

    public void setGui(ToolShopGUI gui) {
        this.gui = gui;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    class ToolCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cardLayout.show(gui.getRootPanel(), "ToolCard");
        }
    }

    class ClearButton1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            gui.getTextField8().setText("");
        }
    }

    class ClearButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            gui.getTextField1().setText("");
            gui.getTextField2().setText("");
            gui.getTextField3().setText("");
            gui.getTextField4().setText("");
            gui.getTextField5().setText("");
            gui.getTextField6().setText("");
            gui.getTextField7().setText("");
        }
    }
}
