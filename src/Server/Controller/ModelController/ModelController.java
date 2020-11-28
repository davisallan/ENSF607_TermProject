package Server.Controller.ModelController;

import Server.Controller.DBController.DBController;
import Server.Controller.ServerController.ServerController;
import CommonModel.Model.Customer;
import CommonModel.Model.Message;
import CommonModel.Model.Order;
import CommonModel.Model.OrderLine;
import CommonModel.Model.Shop;

import java.io.IOException;
import java.sql.ResultSet;

public class ModelController implements Runnable {

    ServerController serverController;
    DBController dbController;
    Shop theShop;
    private ResultSet rs;

    public ModelController(ServerController serverController, DBController dbController, Shop theShop) {
        setTheShop(theShop);
        setDbController(dbController);
        setServerController(serverController);
    }

    @Override
    public void run() {
        while (true) {
            String[] query = serverController.listenForQuery();
            String queryType;
            String condition = "";

            try {
                queryType = query[0];
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }

            if (query.length > 1) {
                condition = query[1]; //the condition of the requested query
            }

            switch (queryType) {
                case "toolId": {
                    toolIdQuery(condition);
                    break;
                }
                case "toolName": {
                    toolNameQuery(condition);
                    break;
                }
                case "allTools": {
                    allToolQuery();
                    break;
                }
                case "customerId": {
                    customerIdQuery(condition);
                    break;
                }
                case "customerLName": {
                    customerLNameQuery(condition);
                    break;
                }
                case "customerType": {
                    customerTypeQuery(condition);
                    break;
                }
                case "updateCustomer": {
                    updateCustomer();
                    break;
                }
                case "sellAllTools": {
                    rs = dbController.selectAllTools();
                    sellToolId(rs, condition);
                    break;
                }
                case "sellToolId": {
                    rs = dbController.searchToolById(Integer.parseInt(condition));
                    sellToolId(rs, condition);
                    break;
                }
                case "sellToolName": {
                    rs = dbController.searchToolByName(condition);
                    sellToolName(rs, condition);
                    break;
                }
                case "deleteCustomer": {
                    dbController.deleteUser(Integer.parseInt(condition));
                    break;
                }
            }
            theShop.clearAllLists();
        }
        closeConnections();
    }

    private void buildTools(ResultSet rs) {
        theShop.buildTool(rs);
    }

    private void buildCustomers(ResultSet rs) {
        theShop.buildCustomers(rs);
    }

    private void sendTools() {
        serverController.sendMessage(new Message("tool"));
        serverController.sendObjects(theShop.getToolList());
    }

    private void sendCustomers() {
        serverController.sendMessage(new Message("customer"));
        serverController.sendObjects(theShop.getCustomerList());
    }

    private void toolIdQuery(String condition) {
        rs = dbController.searchToolById(Integer.parseInt(condition));
        buildTools(rs);
        sendTools();
    }

    private void toolNameQuery(String condition) {
        rs = dbController.searchToolByName(condition);
        buildTools(rs);
        sendTools();
    }

    private void allToolQuery() {
        rs = dbController.selectAllTools();
        buildTools(rs);
        sendTools();
    }

    private void customerIdQuery(String condition) {
        rs = dbController.searchCustomerByID(Integer.parseInt(condition));
        buildCustomers(rs);
        sendCustomers();
    }

    private void customerLNameQuery(String condition) {
        rs = dbController.searchCustomerByLName(condition);
        buildCustomers(rs);
        sendCustomers();
    }

    private void customerTypeQuery(String condition) {
        rs = dbController.searchByCustomerType(condition);
        buildCustomers(rs);
        sendCustomers();
    }

    private void updateCustomer() {
        theShop.getCustomerList().addCustomer((Customer) serverController.listenForObject());
        dbController.updateCustomer(theShop.getCustomerList());
    }

    private void sellToolId(ResultSet rs, String condition) {
        buildTools(rs);
        boolean orderLineCreated = theShop.sellTool(Integer.parseInt(condition));
        dbController.sellTool(Integer.parseInt(condition));
        checkOrderLine(orderLineCreated);
        sendTools();
    }

    private void sellToolName(ResultSet rs, String condition) {
        buildTools(rs);
        boolean orderLineCreated = theShop.sellTool(condition);
        dbController.sellTool(condition);
        checkOrderLine(orderLineCreated);
        sendTools();
    }

    private void checkOrderLine(boolean orderLineCreated) {
        if (orderLineCreated) {
            //Add order
            Order order = theShop.getToolList().getOrder();
            dbController.addOrder(order.getOrderNum(), order.getDate());
            //Add order line
            int size = order.getOrderLines().size();
            OrderLine orderLine = order.getOrderLines().get(size - 1);
            dbController.addOrderLine(orderLine.getOrderId(),
                    orderLine.getToolToOrder().getId(),
                    orderLine.getSupplierId(),
                    orderLine.getOrderQty());
        }
    }

    public void closeConnections() {
        try {
            dbController.close();
            serverController.getObjectIn().close();
            serverController.getObjectOut().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public void setDbController(DBController dbController) {
        this.dbController = dbController;
    }

    public void setTheShop(Shop theShop) {
        this.theShop = theShop;
    }
}