package rucafe.ordermanager;

import java.util.HashMap;
import java.util.Map;

public class DonutTypeMapper {
    private static final Map<String, DonutType> flavorToTypeMap = new HashMap<>();

    static {
        flavorToTypeMap.put("Yeast 1", DonutType.YEAST);
        flavorToTypeMap.put("Yeast 2", DonutType.YEAST);
        flavorToTypeMap.put("Yeast 3", DonutType.YEAST);
        flavorToTypeMap.put("Yeast 4", DonutType.YEAST);
        flavorToTypeMap.put("Yeast 5", DonutType.YEAST);
        flavorToTypeMap.put("Yeast 6", DonutType.YEAST);
        flavorToTypeMap.put("Cake 1", DonutType.CAKE);
        flavorToTypeMap.put("Cake 2", DonutType.CAKE);
        flavorToTypeMap.put("Cake 3", DonutType.CAKE);
        flavorToTypeMap.put("Hole 1", DonutType.HOLE);
        flavorToTypeMap.put("Hole 2", DonutType.HOLE);
        flavorToTypeMap.put("Hole 3", DonutType.HOLE);
    }

    public static DonutType getDonutType(String flavor) {
        return flavorToTypeMap.get(flavor);
    }
}
