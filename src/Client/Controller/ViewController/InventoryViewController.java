package Client.Controller.ViewController;

import Client.View.ClientMgmtGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryViewController {

    private ClientMgmtGUI gui;
    private CardLayout cardLayout;

    public InventoryViewController(ClientMgmtGUI gui) {
        setGui(gui);
        cardLayout = (CardLayout) gui.getRootPanel().getLayout();
        gui.addButtonActionListener(gui.getGoToCustomersButton(), new ClientCardListener());
        gui.addButtonActionListener(gui.getGoToToolsButton(), new ToolCardListener());
    }

    public void setGui(ClientMgmtGUI gui) {
        this.gui = gui;
    }

    class ClientCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cardLayout.show(gui.getRootPanel(), "ClientCard");
        }
    }

    class ToolCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cardLayout.show(gui.getRootPanel(), "ToolCard");
        }
    }


}
