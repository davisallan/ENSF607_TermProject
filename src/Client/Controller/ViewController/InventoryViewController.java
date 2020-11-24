package Client.Controller.ViewController;

import Client.View.CardLayoutGUI;
import Client.View.ClientMgmtGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryViewController implements ActionListener {

    private ClientMgmtGUI gui;
    public InventoryViewController(ClientMgmtGUI gui) {
        setGui(gui);
        addEventListener();
    }

    public void setGui(ClientMgmtGUI gui) {
        this.gui = gui;
    }

    public void addEventListener() {
        JButton button = gui.getButton();
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == gui.getButton()) {
            CardLayout card = (CardLayout) gui.getRootPanel().getLayout();
            card.show(gui.getRootPanel(), "ClientCard");
        }
    }
}
