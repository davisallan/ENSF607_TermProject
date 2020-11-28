package Client.Controller.ViewController;

import Client.Controller.ClientController.ClientController;
import Client.View.ToolShopGUI;
import CommonModel.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientMgmtController {

    private ToolShopGUI gui;
    private CardLayout cardLayout;
    private ClientController clientController;

    public ClientMgmtController(ToolShopGUI gui, ClientController clientController) {
        setGui(gui);
        setClientController(clientController);
        cardLayout = (CardLayout) gui.getRootPanel().getLayout();
        gui.addButtonActionListener(gui.getGoToToolsButton(), new ToolCardListener());
        gui.addButtonActionListener(gui.getClearButton1(), new ClearButtonLeft());
        gui.addButtonActionListener(gui.getClearButton(), new ClearButtonRight());
        gui.addButtonActionListener(gui.getSearchButton(), new SearchButton());
        gui.addButtonActionListener(gui.getSaveButton(), new SaveButton());
        gui.addButtonActionListener(gui.getDeleteButton(), new DeleteButton());
        gui.addMouseListener(gui.getList2(), new List());
    }

    class ToolCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cardLayout.show(gui.getRootPanel(), "ToolCard");
        }
    }

    class ClearButtonLeft implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            clearLeftFields();
        }
    }

    class ClearButtonRight implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            clearRightFields();
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
            saveCustomer();
        }
    }

    class DeleteButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            deleteCustomer();
        }
    }

    class List extends MouseAdapter {
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

    class ShutDown extends WindowAdapter {
        public void WindowClosing(WindowEvent e) {
            clientController.shutDown();
        }
    }

    public void updateGUIResults(CustomerList customerList) {
        DefaultListModel<String> model = new DefaultListModel<>();

        if (customerList.getCustomers().size() == 0)
            gui.getTextField10().setText("Customer does not exist");

        for (Customer customer : customerList.getCustomers())
            model.addElement(customer.getCustomerId() + " " + customer.getFirstName() + " " +
                            customer.getLastName() + " " + customer.getType());

        gui.getList2().setModel(model);
    }

    private void clearLeftFields() {
        gui.getTextField8().setText("");
        DefaultListModel<String> model = new DefaultListModel<>();
        gui.getList2().setModel(model);
        gui.getButtonGroup().clearSelection();
        gui.getTextField10().setText("");
    }

    public void sendSearchQuery() {
        if (gui.getCustomerIDRadioButton().isSelected()) {
            try {
                int customerId = Integer.parseInt(gui.getTextField8().getText());
                String message = "customerId-" + customerId;
                clientController.sendMessage(new Message(message));
            } catch (NumberFormatException e) {
                gui.getTextField10().setText("Please enter an integer");
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

    public void clearRightFields() {
        gui.getTextField1().setText("");
        gui.getTextField2().setText("");
        gui.getTextField3().setText("");
        gui.getTextField4().setText("");
        gui.getTextField5().setText("");
        gui.getTextField6().setText("");
        gui.getTextField7().setText("");
    }

    private void saveCustomer() {
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
        gui.getTextField10().setText("Customer info saved");
    }

    public void deleteCustomer() {
        String message = "deleteCustomer-" + gui.getTextField1().getText();
        clientController.sendMessage(new Message(message));
        clearRightFields();
        sendSearchQuery();
        gui.getTextField10().setText("Customer deleted");
    }

    public void setGui(ToolShopGUI gui) {
        this.gui = gui;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
