package rucafe.ordermanager;

/**
 * Abstract class representing a menu item.
 *
 * @author Woogyeom Sim, Aravind Chendu
 */
public abstract class MenuItem {

    /**
     * Retrieves the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public abstract double price();

    /**
     * Checks if this menu item is equal to another MenuItem object.
     *
     * @param menuItem The MenuItem object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    public abstract boolean equals(MenuItem menuItem);

    /**
     * Returns a string representation of the item.
     *
     * @return A string representation of the item.
     */
    public abstract String toString();

    /**
     * Retrieves the quantity of the menu item.
     *
     * @return The quantity of the menu item.
     */
    public abstract int getQuantity();

    /**
     * Sets the quantity of the menu item.
     *
     * @param quantity The quantity of the menu item to set.
     */
    public abstract void setQuantity(int quantity);

    private int itemNumber;

    /**
     * Sets the item number of the menu item.
     *
     * @param itemNumber The item number to set.
     */
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Retrieves the item number of the menu item.
     *
     * @return The item number of the menu item.
     */
    public int getItemNumber() {
        return this.itemNumber;
    }

}
