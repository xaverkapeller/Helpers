package at.wrdlbrnft.helpers;


import android.widget.TextView;

import java.util.Collection;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Xaver
 * Date: 29.06.13
 * Time: 02:18
 */
public class Strings {

    public static final String EMPTY = "";

    public static <T> String format(String separator, List<T> list) {
        if (list == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        int formattedCount = 0;
        for (T item : list) {
            if (item == null) {
                continue;
            }

            if (formattedCount > 0) {
                builder.append(separator);
            }
            builder.append(item.toString());
            formattedCount++;
        }
        return builder.toString();
    }

    public static String format(String separator, Object... list) {
        if (list == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        int formattedCount = 0;
        for (Object obj : list) {
            if (obj == null) {
                continue;
            }

            if (formattedCount > 0) {
                builder.append(separator);
            }
            builder.append(obj.toString());
            formattedCount++;
        }
        return builder.toString();
    }

    public static String trim(String string) {
        if(string != null) {
            return string.trim();
        }
        return null;
    }

    public static <T> String[] toArray(T[] array) {
        if(array == null) {
            return null;
        }

        final int count = array.length;
        final String[] output = new String[count];
        for(int i = 0; i < count; i++) {
            output[i] = String.valueOf(array[i]);
        }
        return output;
    }

    public static <T> String[] toArray(Collection<T> collection) {
        if(collection == null) {
            return null;
        }

        final int count = collection.size();
        final String[] output = new String[count];

        int i = 0;
        for (T item : collection) {
            output[i++] = String.valueOf(item);
        }
        return output;
    }

    public static String[] array(Object... objects) {
        if(objects == null) {
            return new String[0];
        }

        final int count = objects.length;
        final String[] output = new String[count];
        for(int i = 0; i < count; i++) {
            output[i] = String.valueOf(objects[i]);
        }

        return output;
    }

    public static String from(TextView textView) {
        if(textView != null) {
            return String.valueOf(textView.getText());
        }
        return "";
    }

    public static boolean nullOrEmpty(String string) {
        return string == null || string.isEmpty();

    }

    public static boolean notNullOrEmpty(String string) {
        return string != null && !string.isEmpty();

    }
}