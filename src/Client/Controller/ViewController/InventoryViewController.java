package Client.Controller.ViewController;

import Client.Controller.ClientController.ClientController;
import Client.View.ToolShopGUI;
import Server.Model.Message;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryViewController {

    private ToolShopGUI gui;
    private CardLayout cardLayout;
    private ClientController clientController;

    public InventoryViewController(ToolShopGUI gui, ClientController clientController) {
        setGui(gui);
        setClientController(clientController);
        cardLayout = (CardLayout) gui.getRootPanel().getLayout();
        gui.addButtonActionListener(gui.getGoToCustomersButton(), new ClientCardListener());
        gui.addButtonActionListener(gui.getSearchButton1(), new SearchButton1());
        gui.addButtonActionListener(gui.getClearButton2(), new ClearButton2());
    }

    public void updateGUIResults() {

    }

    public void setGui(ToolShopGUI gui) {
        this.gui = gui;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public ClientController getClientController() {
        return clientController;
    }

    class ClientCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cardLayout.show(gui.getRootPanel(), "ClientCard");
        }
    }

    class SearchButton1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (gui.getToolIDRadioButton().isSelected()) {
                String message = "toolId-" + gui.getTextField9().getText();
                clientController.sendMessage(new Message(message));
            }
            else if (gui.getToolNameRadioButton().isSelected()) {
                String message = "toolName-" + gui.getTextField9().getText();
                clientController.sendMessage(new Message(message));
            }
            else if (gui.getListAllToolsRadioButton().isSelected()) {
                clientController.sendMessage(new Message("allTools"));
            }
        }
    }

    class ClearButton2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            gui.getTextField9().setText("");
        }
    }
}
