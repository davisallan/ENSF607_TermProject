package Client.Controller.ViewController;

import Client.Controller.ClientController.ClientController;
import Client.View.ToolShopGUI;
import Server.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        gui.addButtonActionListener(gui.getSellButton(), new SellButton());
        gui.addMouseListener(gui.getList1(), new List1());
    }

    public void updateGUIResults(ToolList toolList) {
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Tool tool : toolList.getToolList())
            model.addElement(tool.toString());

        gui.getList1().setModel(model);
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
            DefaultListModel<String> model = new DefaultListModel<>();
            gui.getList1().setModel(model);
            gui.getButtonGroup2().clearSelection();
        }
    }

    class SellButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String message = "";
            int index = gui.getList1().getSelectedIndex();
            Tool tool = clientController.getClientModelController().getClientShop().getToolList().getToolList().get(index);
            if (gui.getListAllToolsRadioButton().isSelected())
                message = "sellAllTools-" + tool.getId();
            else if (gui.getToolIDRadioButton().isSelected())
                message = "sellToolId-" + tool.getId();
            else if (gui.getToolNameRadioButton().isSelected())
                message = "sellToolName-" + tool.getName();
            clientController.sendMessage(new Message(message));
            gui.getStatusLabel().setText("Sell of tool " + tool.getName() + " made.");
        }
    }

    class List1 extends MouseAdapter {
        public void mouseClicked(MouseEvent me) {}
    }
}
