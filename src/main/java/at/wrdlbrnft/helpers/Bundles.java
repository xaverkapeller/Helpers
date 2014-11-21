package at.wrdlbrnft.helpers;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Xaver on 22/07/14.
 */
public class Bundles {

    public static Bundle newBundle() {
        return new Bundle();
    }

    public static Bundle newBundle(Bundle bundle) {
        return new Bundle(bundle);
    }

    public static Bundle create(String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        return bundle;
    }

    public static Bundle create(String key, String[] value) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, boolean value) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        return bundle;
    }

    public static Bundle create(String key, boolean[] value) {
        Bundle bundle = new Bundle();
        bundle.putBooleanArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, Bundle value) {
        Bundle bundle = new Bundle();
        bundle.putBundle(key, value);
        return bundle;
    }

    public static Bundle create(String key, byte value) {
        Bundle bundle = new Bundle();
        bundle.putByte(key, value);
        return bundle;
    }

    public static Bundle create(String key, byte[] value) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, char value) {
        Bundle bundle = new Bundle();
        bundle.putChar(key, value);
        return bundle;
    }

    public static Bundle create(String key, char[] value) {
        Bundle bundle = new Bundle();
        bundle.putCharArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, CharSequence value) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(key, value);
        return bundle;
    }

    public static Bundle create(String key, CharSequence[] value) {
        Bundle bundle = new Bundle();
        bundle.putCharSequenceArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, double value) {
        Bundle bundle = new Bundle();
        bundle.putDouble(key, value);
        return bundle;
    }

    public static Bundle create(String key, double[] value) {
        Bundle bundle = new Bundle();
        bundle.putDoubleArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, float value) {
        Bundle bundle = new Bundle();
        bundle.putFloat(key, value);
        return bundle;
    }

    public static Bundle create(String key, float[] value) {
        Bundle bundle = new Bundle();
        bundle.putFloatArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        return bundle;
    }

    public static Bundle create(String key, int[] value) {
        Bundle bundle = new Bundle();
        bundle.putIntArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, long value) {
        Bundle bundle = new Bundle();
        bundle.putLong(key, value);
        return bundle;
    }

    public static Bundle create(String key, long[] value) {
        Bundle bundle = new Bundle();
        bundle.putLongArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, short value) {
        Bundle bundle = new Bundle();
        bundle.putShort(key, value);
        return bundle;
    }

    public static Bundle create(String key, short[] value) {
        Bundle bundle = new Bundle();
        bundle.putShortArray(key, value);
        return bundle;
    }

    public static Bundle create(String key, Serializable value) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, value);
        return bundle;
    }

    public static Bundle create(String key, Parcelable value) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, value);
        return bundle;
    }

    public static Bundle create(String key, Parcelable[] value) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArray(key, value);
        return bundle;
    }
}
