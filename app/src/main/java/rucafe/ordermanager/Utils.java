package rucafe.ordermanager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class containing helper methods for various operations.
 *
 * @author Woogyeom Sim
 */
public class Utils {

    /**
     * Converts a string representation of a donut into a Donut object.
     *
     * @param string The string representation of the donut (format: "Flavor(quantity)").
     * @return The Donut object parsed from the string, or null if parsing fails.
     */
    public static Donut stringToDonut(String string) {
        Pattern pattern = Pattern.compile("^(.+)\\((\\d+)\\)$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            String flavor = matcher.group(1);
            int quantity = Integer.parseInt(matcher.group(2));
            return new Donut(null, flavor, quantity);
        } else {
            return null;
        }
    }

}
