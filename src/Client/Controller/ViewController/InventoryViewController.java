package Client.Controller.ViewController;

import Client.View.ClientMgmtGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryViewController implements ActionListener {

    private ClientMgmtGUI gui;
    private CardLayout cardLayout;

    public InventoryViewController(ClientMgmtGUI gui) {
        setGui(gui);
        cardLayout = (CardLayout) gui.getRootPanel().getLayout();
        addEventListener();
    }

    public void setGui(ClientMgmtGUI gui) {
        this.gui = gui;
    }

    public void addEventListener() {
        for (JButton button : gui.getAllButtons()) {
            button.addActionListener(this);
        }
//        JButton button = gui.getGoToCustomersButton();
//        button.addActionListener(this);
//        button = gui.getGoToToolsButton();
//        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == gui.getGoToCustomersButton())
            cardLayout.show(gui.getRootPanel(), "ClientCard");
        if (actionEvent.getSource() == gui.getGoToToolsButton())
            cardLayout.show(gui.getRootPanel(), "ToolCard");
    }
}
