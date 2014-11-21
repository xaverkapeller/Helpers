package at.wrdlbrnft.helpers;

import android.util.Log;

/**
 * Created by Xaver on 03/07/14.
 */
public class LogHelper {

    public static <T> void error(Class<T> source, String message, Throwable e) {
        Log.e(getLogTagFromClass(source), message, e);
    }

    public static <T> void debug(Class<T> source, String message, Throwable e) {
        Log.d(getLogTagFromClass(source), message, e);
    }

    public static <T> void verbose(Class<T> source, String message, Throwable e) {
        Log.v(getLogTagFromClass(source), message, e);
    }

    public static <T> void info(Class<T> source, String message, Throwable e) {
        Log.i(getLogTagFromClass(source), message, e);
    }

    public static <T> void error(Class<T> source, String message) {
        Log.e(getLogTagFromClass(source), message);
    }

    public static <T> void debug(Class<T> source, String message) {
        Log.d(getLogTagFromClass(source), message);
    }

    public static <T> void verbose(Class<T> source, String message) {
        Log.v(getLogTagFromClass(source), message);
    }

    public static <T> void info(Class<T> source, String message) {
        Log.i(getLogTagFromClass(source), message);
    }

    public static <T> void warn(Class<T> source, String message, Throwable e) {
        Log.w(getLogTagFromClass(source), message, e);
    }

    public static <T> void warn(Class<T> source, String message) {
        Log.w(getLogTagFromClass(source), message);
    }


    public static <T> void warn(Class<T> source, Throwable e) {
        Log.w(getLogTagFromClass(source), e);
    }

    private static <T> String getLogTagFromClass(Class<T> cls) {
        if (cls != null) {
            return cls.getSimpleName();
        }
        return "???";
    }
}
