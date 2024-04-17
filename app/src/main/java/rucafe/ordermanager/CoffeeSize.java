package rucafe.ordermanager;

/**
 * Enum representing different sizes of coffee with associated prices.
 *
 * @author Woogyeom Sim
 */
public enum CoffeeSize {
    SHORT(1.99),
    TALL(2.49),
    GRANDE(2.99),
    VENTI(3.49);

    private final double price;

    /**
     * Constructs a CoffeeSize enum with the specified price.
     *
     * @param price The price of the coffee size.
     */
    CoffeeSize(double price) {
        this.price = price;
    }

    /**
     * Retrieves the price of the coffee size.
     *
     * @return The price of the coffee size.
     */
    public double getPrice() {
        return price;
    }
}
