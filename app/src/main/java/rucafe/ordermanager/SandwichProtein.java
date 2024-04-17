package rucafe.ordermanager;

/**
 * Enumeration representing different types of sandwich proteins.
 *
 * @author Woogyeom Sim
 */
public enum SandwichProtein {
    BEEF(10.99),
    FISH(9.99),
    CHICKEN(8.99);

    private final double price;

    /**
     * Constructs a SandwichProtein with the specified price.
     *
     * @param price The price of the protein.
     */
    SandwichProtein(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the protein.
     *
     * @return The price of the protein.
     */
    public double getPrice() {
        return price;
    }
}
