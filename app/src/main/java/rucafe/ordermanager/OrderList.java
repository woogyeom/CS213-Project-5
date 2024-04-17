package rucafe.ordermanager;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Singleton class responsible for managing orders.
 *
 * @author Aravind Chendu, Woogyeom Sim
 */
public class OrderList {
    private static OrderList instance;
    private final List<Order> orders;
    private Order curOrder;
    private int nextOrderNumber = 1;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    public OrderList() {
        curOrder = new Order();
        orders = new ArrayList<Order>();
    }

    /**
     * Returns the singleton instance of OrderList.
     * @return The singleton instance of OrderList.
     */
    public static OrderList getInstance() {
        if (instance == null) {
            instance = new OrderList();
        }
        return instance;
    }

    /**
     * Retrieves the current order being processed.
     * @return The current order.
     */
    public Order getCurOrder() {
        return curOrder;
    }

    /**
     * Submits an order to the order list.
     * @param order The order to be submitted.
     */
    public void submitOrder(Order order) {
        order.setOrderNumber(nextOrderNumber++);
        orders.add(order);
        curOrder = new Order();
    }

    /**
     * Removes an order from the order list.
     * @param orderNumber The order number of the order to be removed.
     */
    public void removeOrder(int orderNumber) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber() == orderNumber) {
                orders.remove(i);
                return;
            }
        }
    }

    /**
     * Retrieves the details of an order by its order number.
     * @param orderNumber The order number of the order to be retrieved.
     * @return The details of the order as a string.
     */
    public String getOrder(int orderNumber) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber() == orderNumber) {
                return orders.get(i).toString();
            }
        }
        return "Order Number Does Not Exist";
    }

    /**
     * Saves the list of orders to a file.
     */
    public void saveOrdersToFile() {
        try (PrintWriter pw = new PrintWriter(new File("orders.txt"))) {
            for (Order order : orders) {
                pw.println(order.toString());
                pw.println("-----");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to save orders to the file: " + e.getMessage());
        }
    }

    /**
     * Retrieves the list of all orders.
     * @return The list of all orders.
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }


}
