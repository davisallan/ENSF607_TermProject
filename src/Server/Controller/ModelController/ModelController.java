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

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public void setDbController(DBController dbController) {
        this.dbController = dbController;
    }

    public void setTheShop(Shop theShop) {
        this.theShop = theShop;
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

    @Override
    public void run() {
        while (true) {
            String[] query = serverController.listenForQuery();
            String queryType = query[0]; //the type of requested query
            String condition = "";

            if (queryType.equals("quit")) {
                serverController.sendMessage(new Message("quit"));
                System.out.println("Client has disconnected...");
                break;
            }

            if (query.length > 1) {
                condition = query[1]; //the condition of the requested query
            }

            switch (queryType) {
                case "toolId": {
                    rs = dbController.searchToolById(Integer.parseInt(condition));
                    theShop.buildTool(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "toolName": {
                    rs = dbController.searchToolByName(condition);
                    theShop.buildTool(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "allTools": {
                    rs = dbController.selectAllTools();
                    theShop.buildTool(rs);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "customerId": {
                    rs = dbController.searchCustomerByID(Integer.parseInt(condition));
                    theShop.buildCustomers(rs);
                    serverController.sendMessage(new Message("customer"));
                    serverController.sendObjects(theShop.getCustomerList());
                    break;
                }
                case "customerLName": {
                    rs = dbController.searchCustomerByLName(condition);
                    theShop.buildCustomers(rs);
                    serverController.sendMessage(new Message("customer"));
                    serverController.sendObjects(theShop.getCustomerList());
                    break;
                }
                case "customerType": {
                    rs = dbController.searchByCustomerType(condition);
                    theShop.buildCustomers(rs);
                    serverController.sendMessage(new Message("customer"));
                    serverController.sendObjects(theShop.getCustomerList());
                    break;
                }
                case "updateCustomer": {
                    theShop.getCustomerList().addCustomer((Customer) serverController.listenForObject());
                    dbController.updateUser(theShop.getCustomerList());
                    break;
                }
                case "sellAllTools": {
                    rs = dbController.selectAllTools();
                    theShop.buildTool(rs);
                    boolean orderLineCreated = theShop.sellItem(Integer.parseInt(condition));
                    dbController.sellItem(Integer.parseInt(condition));
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
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "sellToolId": {
                    rs = dbController.searchToolById(Integer.parseInt(condition));
                    theShop.buildTool(rs);
                    theShop.sellItem(Integer.parseInt(condition));
                    dbController.sellItem(Integer.parseInt(condition));
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
                    break;
                }
                case "sellToolName": {
                    rs = dbController.searchToolByName(condition);
                    theShop.buildTool(rs);
                    theShop.sellItem(condition);
                    dbController.sellItem(condition);
                    serverController.sendMessage(new Message("tool"));
                    serverController.sendObjects(theShop.getToolList());
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
}


