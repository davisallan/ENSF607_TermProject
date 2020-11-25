package Client.Controller.ViewController;

import Client.View.ClientMgmtGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMgmtController {

    private ClientMgmtGUI gui;
    private CardLayout cardLayout;

    public ClientMgmtController(ClientMgmtGUI gui) {
        setGui(gui);
        cardLayout = (CardLayout) gui.getRootPanel().getLayout();
        gui.addButtonActionListener(gui.getGoToToolsButton(), new ClientMgmtController.ToolCardListener());
        gui.addButtonActionListener(gui.getClearButton1(), new ClientMgmtController.ClearButton1());
        gui.addButtonActionListener(gui.getClearButton(), new ClientMgmtController.ClearButton());
    }

    public void setGui(ClientMgmtGUI gui) {
        this.gui = gui;
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
