package rucafe.ordermanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order containing multiple menu items.
 *
 * @author Aravind Chendu, Woogyeom Sim
 */
public class Order {
    private final List<MenuItem> items;
    private int orderNumber;
    private int nextItemNumber = 1;

    /**
     * Constructs an Order object.
     */
    public Order() {
        this.items = new ArrayList<MenuItem>();
    }

    /**
     * Adds a menu item to the order.
     *
     * @param item The menu item to add.
     */
    public void addItem(MenuItem item) {
        item.setItemNumber(nextItemNumber++);
        items.add(item);
    }

    /**
     * Removes a menu item from the order by item number.
     *
     * @param itemNumber The item number of the menu item to remove.
     */
    public void removeItem(int itemNumber) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemNumber() == itemNumber) {
                items.remove(i);
                return;
            }
        }
    }

    /**
     * Removes a menu item from the order.
     *
     * @param menuItem The menu item to remove.
     */
    public void removeItem(MenuItem menuItem) {
        MenuItem itemToRemove = null;
        for (MenuItem item : items) {
            if (item.equals(menuItem)) {
                itemToRemove = item;
            }
        }
        if (itemToRemove != null) items.remove(itemToRemove);
    }

    /**
     * Retrieves the list of menu items in the order.
     *
     * @return The list of menu items.
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Calculates the subtotal of the order.
     *
     * @return The subtotal of the order.
     */
    public double getSubTotal() {
        double subtotal = 0;
        for (MenuItem item : items) {
            subtotal += item.price();
        }
        return subtotal;
    }

    /**
     * Calculates the sales tax of the order.
     *
     * @return The sales tax of the order.
     */
    public double getSalesTax() {
        return getSubTotal() * 0.0625;
    }

    /**
     * Calculates the total amount of the order including sales tax.
     *
     * @return The total amount of the order.
     */
    public double getTotalAmount() {
        return getSubTotal() + getSalesTax();
    }

    /**
     * Retrieves the order number.
     *
     * @return The order number.
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Sets the order number.
     *
     * @param orderNumber The order number to set.
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Checks if the order contains a specific menu item.
     *
     * @param menuItem The menu item to check.
     * @return True if the order contains the menu item, false otherwise.
     */
    public boolean contains(MenuItem menuItem) {
        for (MenuItem item : items) {
            if (item.equals(menuItem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and retrieves a specific menu item in the order.
     *
     * @param menuItem The menu item to find.
     * @return The found menu item, or null if not found.
     */
    public MenuItem find(MenuItem menuItem) {
        for (MenuItem item : items) {
            if (item.equals(menuItem)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the order.
     *
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        String returnString = "Order Number: " + this.orderNumber + "\n";
        for (int i = 0; i < items.size(); i++) {
            returnString += items.get(i).toString() + "\n";
        }
        returnString += "\nSub Total: $" + String.format("%.2f", this.getSubTotal());
        returnString += "\nSales Tax: $" + String.format("%.2f", this.getSalesTax());
        returnString += "\nTotal Amount: $" + String.format("%.2f", this.getTotalAmount());
        return returnString;
    }
}
