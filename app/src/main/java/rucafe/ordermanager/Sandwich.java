package rucafe.ordermanager;

import java.util.Arrays;

/**
 * Represents a sandwich item.
 *
 * @author Woogyeom Sim
 */
public class Sandwich extends MenuItem{
    private String bread;
    private SandwichProtein protein;
    private boolean[] add_ons;
    // [cheese, lettuce, tomato, onion]
    private int quantity;
    private static final double CHEESE_ADD_ON_PRICE = 1.00;
    private static final double VEGI_ADD_ON_PRICE = 0.30;

    /**
     * Constructs a sandwich with the specified parameters.
     *
     * @param bread     The type of bread for the sandwich.
     * @param protein   The protein (meat) for the sandwich.
     * @param add_ons   The additional toppings for the sandwich.
     * @param quantity  The quantity of sandwiches.
     */
    public Sandwich(String bread, SandwichProtein protein, boolean[] add_ons, int quantity) {
        this.bread = bread;
        this.protein = protein;
        this.add_ons = add_ons;
        this.quantity = quantity;
    }

    /**
     * Gets the type of bread for the sandwich.
     *
     * @return The type of bread.
     */
    public String getBread() {
        return bread;
    }

    /**
     * Sets the type of bread for the sandwich.
     *
     * @param bread The type of bread.
     */
    public void setBread(String bread) {
        this.bread = bread;
    }

    /**
     * Gets the protein (meat) for the sandwich.
     *
     * @return The protein (meat).
     */
    public SandwichProtein getProtein() {
        return protein;
    }

    /**
     * Sets the protein (meat) for the sandwich.
     *
     * @param protein The protein (meat).
     */
    public void setProtein(SandwichProtein protein) {
        this.protein = protein;
    }

    /**
     * Gets the additional toppings for the sandwich.
     *
     * @return The additional toppings.
     */
    public boolean[] getAdd_ons() {
        return add_ons;
    }

    /**
     * Sets the additional toppings for the sandwich.
     *
     * @param add_ons The additional toppings.
     */
    public void setAdd_ons(boolean[] add_ons) {
        this.add_ons = add_ons;
    }

    /**
     * Gets the quantity of sandwiches.
     *
     * @return The quantity of sandwiches.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of sandwiches.
     *
     * @param quantity The quantity of sandwiches.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the price of the sandwich.
     *
     * @return The price of the sandwich.
     */
    @Override
    public double price() {
        int cheese_count = 0;
        int vegi_count = 0;
        for (int i = 0; i < add_ons.length; i++) {
            if (add_ons[i]) {
                if (i == 0) {
                    cheese_count++;
                } else {
                    vegi_count++;
                }
            }
        }
        return (protein.getPrice() + (cheese_count * CHEESE_ADD_ON_PRICE) + (vegi_count * VEGI_ADD_ON_PRICE)) * quantity;
    }

    /**
     * Checks if this sandwich is equal to another MenuItem.
     *
     * @param menuItem The MenuItem to compare.
     * @return True if the sandwiches are equal, otherwise false.
     */
    @Override
    public boolean equals(MenuItem menuItem) {
        if (menuItem instanceof Sandwich sandwich) {
            return bread.equals(sandwich.getBread()) && protein.equals(sandwich.getProtein()) && Arrays.equals(add_ons, sandwich.getAdd_ons());
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the sandwich.
     *
     * @return The string representation of the sandwich.
     */
    @Override
    public String toString() {
        String returnString = "(SANDWICH) [Item Number: " + this.getItemNumber() + "] Bread: " + bread + " Protein: " + protein.toString() + " Add-ons:";
        String add = "";
        if (add_ons[0]) add += " Cheese,";
        if (add_ons[1]) add += " Lettuce,";
        if (add_ons[2]) add += " Tomatoes,";
        if (add_ons[3]) add += " Onions,";
        if (add.isEmpty()) add = " None,";
        returnString += add;

        returnString += " Quantity: " + quantity + " PRICE: $" + String.format("%.2f", this.price());

        return returnString;
    }

}
