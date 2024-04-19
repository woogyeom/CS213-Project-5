package rucafe.ordermanager;

import java.util.Arrays;

/**
 * Represents a coffee item that extends the MenuItem class.
 *
 * @author Woogyeom Sim, Aravind Chendu
 */
public class Coffee extends MenuItem {
    private CoffeeSize coffeeSize;
    private boolean[] add_ins;
    // [sweet cream, French vanilla, Irish cream, caramel, mocha]
    private int quantity;
    private static final double ADD_IN_PRICE = 0.30;

    /**
     * Constructs a Coffee object with the specified size, add-ins, and quantity.
     *
     * @param coffeeSize The size of the coffee.
     * @param add_ins    An array representing additional ingredients.
     * @param quantity   The quantity of the coffee.
     */
    public Coffee(CoffeeSize coffeeSize, boolean[] add_ins, int quantity) {
        this.coffeeSize = coffeeSize;
        this.add_ins = add_ins;
        this.quantity = quantity;
    }

    /**
     * Retrieves the size of the coffee.
     *
     * @return The size of the coffee.
     */
    public CoffeeSize getCoffeeSize() {
        return coffeeSize;
    }

    /**
     * Sets the size of the coffee.
     *
     * @param coffeeSize The size of the coffee to set.
     */
    public void setCoffeeSize(CoffeeSize coffeeSize) { this.coffeeSize = coffeeSize; }

    /**
     * Retrieves the array representing additional ingredients.
     *
     * @return An array representing additional ingredients.
     */
    public boolean[] getAdd_ins() {
        return add_ins;
    }

    /**
     * Sets the array representing additional ingredients.
     *
     * @param add_ins The array representing additional ingredients to set.
     */
    public void setAdd_ins(boolean[] add_ins) {
        this.add_ins = add_ins;
    }

    public void setAdd_in(int id, boolean bool) {
        add_ins[id] = bool;
    }

    /**
     * Retrieves the quantity of the coffee.
     *
     * @return The quantity of the coffee.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the coffee.
     *
     * @param quantity The quantity of the coffee to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the price of the coffee item based on size, add-ins, and quantity.
     *
     * @return The price of the coffee.
     */
    @Override
    public double price() {
        int count = 0;
        for (boolean value : add_ins) {
            if (value) {
                count++;
            }
        }
        return (coffeeSize.getPrice() + (count * ADD_IN_PRICE)) * quantity;
    }

    /**
     * Checks if this coffee item is equal to another MenuItem object.
     *
     * @param menuItem The MenuItem object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(MenuItem menuItem) {
        if (menuItem instanceof Coffee coffee) {
            return this.coffeeSize.equals(coffee.coffeeSize) && Arrays.equals(this.add_ins, coffee.add_ins);
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the coffee item.
     *
     * @return A string representation of the coffee item.
     */
    @Override
    public String toString() {
        String returnString = "(COFFEE) [Item Number: " + this.getItemNumber() + "]  size: " + coffeeSize + " Add-ins:";
        String add = "";
        if (add_ins[0]) add += " Sweet Cream,";
        if (add_ins[1]) add += " French Vanilla,";
        if (add_ins[2]) add += " Irish Cream,";
        if (add_ins[3]) add += " Caramel,";
        if (add_ins[4]) add += " mocha,";
        if (add.isEmpty()) add = " None,";

        returnString += add;
        returnString += " Quantity: " + quantity + " PRICE: $" + String.format("%.2f", this.price());
        return returnString;
    }
}
