package Client.Controller.ViewController;

import Client.Controller.ClientController.ClientController;
import Client.View.ToolShopGUI;
import CommonModel.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientMgmtController {

    private ToolShopGUI gui;
    private CardLayout cardLayout;
    private ClientController clientController;

    public ClientMgmtController(ToolShopGUI gui, ClientController clientController) {
        setGui(gui);
        setClientController(clientController);
        cardLayout = (CardLayout) gui.getRootPanel().getLayout();
        gui.addButtonActionListener(gui.getGoToToolsButton(), new ToolCardListener());
        gui.addButtonActionListener(gui.getClearButton1(), new ClearButton1());
        gui.addButtonActionListener(gui.getClearButton(), new ClearButton());
        gui.addButtonActionListener(gui.getSearchButton(), new SearchButton());
        gui.addButtonActionListener(gui.getSaveButton(), new SaveButton());
        gui.addButtonActionListener(gui.getDeleteButton(), new DeleteButton());
        gui.addMouseListener(gui.getList2(), new List2());
    }

    public void updateGUIResults(CustomerList customerList) {
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Customer customer : customerList.getCustomers())
            model.addElement(customer.getCustomerId() + " " + customer.getFirstName() + " " +
                            customer.getLastName() + " " + customer.getType());

        gui.getList2().setModel(model);
    }

    public void sendSearchQuery() {
        if (gui.getCustomerIDRadioButton().isSelected()) {
            try {
                int customerId = Integer.parseInt(gui.getTextField8().getText());
                String message = "customerId-" + customerId;
                clientController.sendMessage(new Message(message));
            } catch (NumberFormatException ignored) {
            }
        }
        else if (gui.getLastNameRadioButton().isSelected()) {
            String message = "customerLName-" + gui.getTextField8().getText();
            clientController.sendMessage(new Message(message));
        }
        else if (gui.getClientTypeRadioButton().isSelected()) {
            String clientType = gui.getTextField8().getText();
            if (clientType.equalsIgnoreCase("C") || clientType.equalsIgnoreCase("R")) {
                String message = "customerType-" + clientType;
                clientController.sendMessage(new Message(message));
            }
        }
    }

    public void clearTextFields() {
        gui.getTextField1().setText("");
        gui.getTextField2().setText("");
        gui.getTextField3().setText("");
        gui.getTextField4().setText("");
        gui.getTextField5().setText("");
        gui.getTextField6().setText("");
        gui.getTextField7().setText("");
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
            DefaultListModel<String> model = new DefaultListModel<>();
            gui.getList2().setModel(model);
            gui.getButtonGroup().clearSelection();
        }
    }

    class ClearButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            clearTextFields();
        }
    }

    class SearchButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            sendSearchQuery();
        }
    }

    class SaveButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String[] customerInfo = new String[7];
            customerInfo[0] = gui.getTextField1().getText();
            customerInfo[1] = gui.getTextField2().getText();
            customerInfo[2] = gui.getTextField3().getText();
            customerInfo[3] = gui.getTextField4().getText();
            customerInfo[4] = gui.getTextField5().getText();
            customerInfo[5] = gui.getTextField6().getText();
            customerInfo[6] = gui.getTextField7().getText();

            if (customerInfo[6].equals("R")) {
                clientController.sendMessage(new Message("updateCustomer"));
                clientController.sendCustomer(clientController.getClientModelController()
                                .getClientShop().getCustomerList()
                                .buildResidential(customerInfo));
            } else if (customerInfo[6].equalsIgnoreCase("C")) {
                clientController.sendMessage(new Message("updateCustomer"));
                clientController.sendCustomer(clientController.getClientModelController()
                                .getClientShop().getCustomerList()
                                .buildCommercial(customerInfo));
            }
        }
    }

    class DeleteButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String message = "deleteCustomer-" + gui.getTextField1().getText();
            clientController.sendMessage(new Message(message));
            clearTextFields();
            sendSearchQuery();
        }
    }

    class List2 extends MouseAdapter {
        public void mouseClicked(MouseEvent me) {
            JList selection = (JList)me.getSource();
            int index = selection.locationToIndex(me.getPoint());
            Customer customer = clientController.getClientModelController().getClientShop().getCustomerList().getCustomers().get(index);
            gui.getTextField1().setText(Integer.toString(customer.getCustomerId()));
            gui.getTextField2().setText(customer.getFirstName());
            gui.getTextField3().setText(customer.getLastName());
            gui.getTextField4().setText(customer.getAddress());
            gui.getTextField5().setText(customer.getPostalCode());
            gui.getTextField6().setText(customer.getPhoneNum());
            gui.getTextField7().setText(String.valueOf(customer.getType()));
        }
    }
}
