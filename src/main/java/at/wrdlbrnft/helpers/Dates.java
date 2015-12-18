package at.wrdlbrnft.helpers;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import at.wrdlbrnft.helpers.time.Time;

/**
 * Created by Xaver on 02/07/14.
 */
public class Dates {

    private static final DateFormat dateFormat = DateFormat.getDateInstance();
    private static final DateFormat dateTimeFormat = DateFormat.getDateTimeInstance();
    private static final DateFormat timeFormat = DateFormat.getTimeInstance();
    private static final DateFormat shortTimeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
    private static final DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        }
        return null;
    }
    
    public static String format(Date date, String pattern, Locale locale) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
            return dateFormat.format(date);
        }
        return null;
    }

    public static String formatDate(Date date) {
        if (date != null) {
            return dateFormat.format(date);
        }
        return null;
    }

    public static String formatDateTime(Date date) {
        if (date != null) {
            return dateTimeFormat.format(date);
        }
        return null;
    }

    public static String formatTime(Date date) {
        if (date != null) {
            return timeFormat.format(date);
        }
        return null;
    }

    public static String formatShortTime(Date date) {
        if (date != null) {
            return shortTimeFormat.format(date);
        }
        return null;
    }

    public static String formatShortDate(Date date) {
        if (date != null) {
            return shortDateFormat.format(date);
        }
        return null;
    }

    public static Date parse(String dateString, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(dateString);
    }
    
    public static Date parse(String dateString, String pattern, Locale locale) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
        return dateFormat.parse(dateString);
    }

    public static Date parse(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }

    public static Date from(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar.getTime();
    }

    public static Date now() {
        return new Date();
    }

    public static boolean isInThePast(Date date) {
        final Date now = new Date();
        return now.after(date);
    }

    public static boolean isInTheFuture(Date date) {
        final Date now = new Date();
        return now.before(date);
    }

    public static Date combine(Date date, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date combine(Date date, Time time) {
        return combine(date, time.getHour(), time.getMinute());
    }

    public static Date nowWithOffset(long millis) {
        return new Date(System.currentTimeMillis() + millis);
    }

    public static Date nowWithOffset(long seconds, long millis) {
        return new Date(System.currentTimeMillis() + millisOf(seconds, millis));
    }

    public static Date nowWithOffset(long minutes, long seconds, long millis) {
        return new Date(System.currentTimeMillis() + millisOf(minutes, seconds, millis));
    }

    public static Date nowWithOffset(long hours, long minutes, long seconds, long millis) {
        return new Date(System.currentTimeMillis() + millisOf(hours, minutes, seconds, millis));
    }

    public static Date nowWithOffset(long days, long hours, long minutes, long seconds, long millis) {
        return new Date(System.currentTimeMillis() + millisOf(days, hours, minutes, seconds, millis));
    }

    public static Date dateWithOffset(Date date, long millis) {
        return new Date(millisOf(date) + millis);
    }

    public static Date dateWithOffset(Date date, long seconds, long millis) {
        return new Date(millisOf(date) + millisOf(seconds, millis));
    }

    public static Date dateWithOffset(Date date, long minutes, long seconds, long millis) {
        return new Date(millisOf(date) + millisOf(minutes, seconds, millis));
    }

    public static Date dateWithOffset(Date date, long hours, long minutes, long seconds, long millis) {
        return new Date(millisOf(date) + millisOf(hours, minutes, seconds, millis));
    }

    public static Date dateWithOffset(Date date, long days, long hours, long minutes, long seconds, long millis) {
        return new Date(millisOf(date) + millisOf(days, hours, minutes, seconds, millis));
    }

    public static long millisOf(Date date) {
        if(date == null) {
            return 0l;
        }

        return date.getTime();
    }

    public static long millisOf(long seconds, long millis) {
        return seconds * 1000l + millis;
    }

    public static long millisOf(long minutes, long seconds, long millis) {
        return (minutes * 60l + seconds) * 1000l + millis;
    }

    public static long millisOf(long hours, long minutes, long seconds, long millis) {
        return ((hours * 60l + minutes) * 60l + seconds) * 1000l + millis;
    }

    public static long millisOf(long days, long hours, long minutes, long seconds, long millis) {
        return (((days * 24l + hours) * 60l + minutes) * 60l + seconds) * 1000l + millis;
    }

    public static Date newDate(long millis) {
        return new Date(millis);
    }

    public static int getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static int getDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDayDifference(Date date) {
        return getDayDifference(now(), date);
    }

    public static int getDayDifference(Date a, Date b) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a);
        int aYear = calendar.get(Calendar.YEAR);
        int aDays = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(b);
        int bYear = calendar.get(Calendar.YEAR);
        int bDays = calendar.get(Calendar.DAY_OF_YEAR);

        return Math.abs((aYear - bYear) * 365 + aDays - bDays);
    }

    public static Date getDayStartOf(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDayStart() {
        return getDayStartOf(now());
    }

    public static Date getDayEndOf(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();

    }

    public static Date getDayEnd() {
        return getDayEndOf(now());
    }

    public static Date create(int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        return calendar.getTime();
    }

    public static Date create(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        return calendar.getTime();
    }

    public static Date create(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minute, second);
        return calendar.getTime();
    }

    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}
