package rucafe.ordermanager;

/**
 * Enum representing different types of donuts with associated prices, display names, and flavors.
 *
 * @author Woogyeom Sim
 */
public enum DonutType {
    YEAST(1.79, "yeast donuts", new String[]{"Yeast 1", "Yeast 2", "Yeast 3", "Yeast 4", "Yeast 5", "Yeast 6"}),
    CAKE(1.89, "cake donuts", new String[]{"Cake 1", "Cake 2", "Cake 3"}),
    HOLE(0.39, "donut holes", new String[]{"Hole 1", "Hole 2", "Hole 3"});

    private final double price;
    private final String displayName;
    private final String[] flavors;

    /**
     * Constructs a DonutType enum with the specified price, display name, and flavors.
     *
     * @param price        The price of the donut type.
     * @param displayName  The display name of the donut type.
     * @param flavors      The flavors available for the donut type.
     */
    DonutType(double price, String displayName, String[] flavors) {
        this.price = price;
        this.displayName = displayName;
        this.flavors = flavors;
    }

    /**
     * Retrieves the price of the donut type.
     *
     * @return The price of the donut type.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the display name of the donut type.
     *
     * @return The display name of the donut type.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Retrieves the flavors available for the donut type.
     *
     * @return The flavors available for the donut type.
     */
    public String[] getFlavors() {
        return flavors;
    }

    /**
     * Retrieves the DonutType enum from its display name.
     *
     * @param displayName The display name of the donut type.
     * @return The DonutType enum corresponding to the display name, or null if not found.
     */
    public static DonutType fromDisplayName(String displayName) {
        for (DonutType type : DonutType.values()) {
            if (type.getDisplayName().equals(displayName)) {
                return type;
            }
        }
        return null;
    }

}
