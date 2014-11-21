package at.wrdlbrnft.helpers;

/**
 * Created by Xaver on 24/07/14.
 */
public class Numbers {

    public static int intValue(long value) {
        final Long object = value;
        return object.intValue();
    }

    public static int intValue(short value) {
        final Short object = value;
        return object.intValue();
    }

    public static short shortValue(long value) {
        final Long object = value;
        return object.shortValue();
    }

    public static short shortValue(int value) {
        final Integer object = value;
        return object.shortValue();
    }

    public static long longValue(int value) {
        final Integer object = value;
        return object.longValue();
    }

    public static long longValue(short value) {
        final Short object = value;
        return object.longValue();
    }

    public static int range(int value, int min, int max) {
        if(value < min) {
            return min;
        }

        if(value > max) {
            return max;
        }

        return value;
    }

    public static float range(float value, float min, float max) {
        if(value < min) {
            return min;
        }

        if(value > max) {
            return max;
        }

        return value;
    }

    public static double range(double value, double min, double max) {
        if(value < min) {
            return min;
        }

        if(value > max) {
            return max;
        }

        return value;
    }

    public static short range(short value, short min, short max) {
        if(value < min) {
            return min;
        }

        if(value > max) {
            return max;
        }

        return value;
    }
}
