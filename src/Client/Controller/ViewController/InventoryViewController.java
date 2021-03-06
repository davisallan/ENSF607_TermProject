package Client.Controller.ViewController;

import Client.Controller.ClientController.ClientController;
import Client.View.ToolShopGUI;
import CommonModel.Model.*;

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
        gui.addButtonActionListener(gui.getSearchButton1(), new SearchButton());
        gui.addButtonActionListener(gui.getClearButton2(), new ClearButton());
        gui.addButtonActionListener(gui.getSellButton(), new SellButton());
        gui.addMouseListener(gui.getList1(), new List());
    }

    class ClientCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cardLayout.show(gui.getRootPanel(), "ClientCard");
        }
    }

    class SearchButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            sendSearchQuery();
        }
    }

    class ClearButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            clearGUI();
        }
    }

    class SellButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            sellTool();
        }
    }

    class List extends MouseAdapter {
        public void mouseClicked(MouseEvent me) {}
    }

    class ShutDown extends WindowAdapter {
        public void WindowClosing(WindowEvent e) {
            clientController.shutDown();
        }
    }

    public void updateGUIResults(ToolInventory toolInventory) {
        DefaultListModel<String> model = new DefaultListModel<>();

        if (toolInventory.getToolList().size() == 0)
            gui.getTextField11().setText("Tool does not exist");

        for (Tool tool : toolInventory.getToolList())
            model.addElement(tool.toString());

        gui.getList1().setModel(model);
    }

    private void sendSearchQuery() {
        if (gui.getToolIDRadioButton().isSelected()) {
            try {
                int toolId = Integer.parseInt(gui.getTextField9().getText());
                String message = "toolId-" + toolId;
                clientController.sendMessage(new Message(message));
            } catch (NumberFormatException e) {
                gui.getTextField11().setText("Please enter an integer");
            }
        }
        else if (gui.getToolNameRadioButton().isSelected()) {
            String message = "toolName-" + gui.getTextField9().getText();
            clientController.sendMessage(new Message(message));
        }
        else if (gui.getListAllToolsRadioButton().isSelected()) {
            clientController.sendMessage(new Message("allTools"));
        }
    }

    private void clearGUI() {
        gui.getTextField9().setText("");
        DefaultListModel<String> model = new DefaultListModel<>();
        gui.getList1().setModel(model);
        gui.getButtonGroup1().clearSelection();
        gui.getTextField11().setText(" ");
    }

    private void sellTool() {
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
        gui.getTextField11().setText("Sale completed");
    }

    public void setGui(ToolShopGUI gui) {
        this.gui = gui;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
