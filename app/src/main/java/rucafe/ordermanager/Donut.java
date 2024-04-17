package rucafe.ordermanager;

/**
 * Represents a donut item that extends the MenuItem class.
 *
 * @author Woogyeom Sim, Aravind Chendu
 */
public class Donut extends MenuItem {
    private final DonutType type;
    private final String flavor;
    private int quantity;

    /**
     * Constructs a Donut object with the specified type, flavor, and quantity.
     *
     * @param type     The type of the donut.
     * @param flavor   The flavor of the donut.
     * @param quantity The quantity of the donut.
     */
    public Donut(DonutType type, String flavor, int quantity) {
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /**
     * Retrieves the type of the donut.
     *
     * @return The type of the donut.
     */
    public DonutType getType() {
        return type;
    }

    /**
     * Retrieves the flavor of the donut.
     *
     * @return The flavor of the donut.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Retrieves the quantity of the donut.
     *
     * @return The quantity of the donut.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the donut.
     *
     * @param quantity The quantity of the donut to set.
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Calculates the price of the donut item based on type and quantity.
     *
     * @return The price of the donut.
     */
    @Override
    public double price() {
        return type.getPrice() * quantity;
    }

    /**
     * Checks if this donut item is equal to another MenuItem object.
     *
     * @param menuItem The MenuItem object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(MenuItem menuItem) {
        if (menuItem instanceof Donut donut) {
            return flavor.equals(donut.getFlavor());
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the donut item.
     *
     * @return A string representation of the donut item.
     */
    @Override
    public String toString() {
        return "(DONUT) [Item Number: " + getItemNumber() + "] Type: " + type.getDisplayName() + " Flavor: " + flavor + " Quantity: " + quantity + ", PRICE: $" + String.format("%.2f", price());
    }
}
