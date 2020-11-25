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
        gui.addButtonActionListener(gui.getClearButton(), new ClearButton());
        gui.addButtonActionListener(gui.getClearButton1(), new ClearButton1());
        gui.addButtonActionListener(gui.getClearButton2(), new ClearButton2());
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

    class ClearButton1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            gui.getTextField8().setText("");
        }
    }

    class ClearButton2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            gui.getTextField9().setText("");
        }
    }
}
