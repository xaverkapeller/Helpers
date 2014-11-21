package at.wrdlbrnft.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xaver on 16/07/14.
 */
public class Maps {

    public static <K, V> Map<K, V> newMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> Map<K, V> newMap(Map<K, V> map) {
        return new HashMap<K, V>(map);
    }
}
