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
        gui.addButtonActionListener(gui.getSearchButton1(), new SearchButton1());
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

    class SearchButton1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (gui.getToolIDRadioButton().isSelected()) {
                System.out.println(gui.getTextField9().getText());
            }
            else if (gui.getToolNameRadioButton().isSelected()) {
                System.out.println("Name");
            }
            else if (gui.getListAllToolsRadioButton().isSelected()) {
                System.out.println("All tools");
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
