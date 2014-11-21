package at.wrdlbrnft.helpers;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Xaver on 17/07/14.
 */
public class Sets {

    public static <T> Set<T> newSet() {
        return new HashSet<T>();
    }

    public static <T> Set<T> newSet(Collection<? extends T> collection) {
        return new HashSet<T>(collection);
    }

    public static <T> Set<T> newSet(T... array) {
        final Set<T> set = new HashSet<T>();
        Collections.addAll(set, array);
        return set;
    }

    public static <T> T[] toArray(Class<T> cls, Set<? extends T> set) {
        if (set == null) {
            return null;
        }

        final T[] array = (T[]) Array.newInstance(cls, set.size());
        return set.toArray(array);
    }
}
