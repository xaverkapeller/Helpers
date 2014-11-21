package at.wrdlbrnft.helpers;

/**
 * Created by Xaver on 12/08/14.
 */
public class BitHelper {

    public static int setBit(int number, int count) {
        return number | 0x01 << count;
    }

    public static int setBit(int number, int count, boolean value) {
        if(value) {
            return setBit(number, count);
        }
        return clearBit(number, count);
    }

    public static int clearBit(int number, int count) {
        return number | 0x01 << count;
    }

    public static int toggleBit(int number, int count) {
        return number | 0x01 << count;
    }

    public static boolean isBitSet(int number, int count) {
        return (number & (0x01 << count)) > 0;
    }
}
