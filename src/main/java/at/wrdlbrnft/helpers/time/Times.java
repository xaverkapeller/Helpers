package at.wrdlbrnft.helpers.time;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import at.wrdlbrnft.helpers.Dates;


/**
 * Created by Xaver on 21/07/14.
 */
public class Times {

    public static Time create(int hour, int minute) {
        return new TimeImpl(hour, minute);
    }

    public static Time create() {
        return new TimeImpl();
    }

    public static Time from(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimeImpl(hourOfDay, minute);
    }

    public static Time now() {
        final Calendar calendar = Calendar.getInstance();
        final int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final Time time = new TimeImpl();
        time.setHour(hourOfDay);
        time.setMinute(minute);
        return time;
    }

    private static class TimeImpl implements Time {

        private static final DecimalFormat decimalFormat = new DecimalFormat("00");

        private static final int HOURS_IN_DAY = 24;
        private static final int MINUTES_IN_HOUR = 60;

        private int hour = 0;
        private int minute = 0;

        private TimeImpl(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        private TimeImpl() {
        }

        @Override
        public int getHour() {
            return this.hour;
        }

        @Override
        public void setHour(int hour) {
            this.hour = hour % HOURS_IN_DAY;
        }

        @Override
        public int getMinute() {
            return this.minute;
        }

        @Override
        public void setMinute(int minute) {
            this.minute = minute % MINUTES_IN_HOUR;
        }

        @Override
        public boolean isSet() {
            return hour != Integer.MIN_VALUE && minute != Integer.MIN_VALUE;
        }

        @Override
        public long toMillis() {
            return Dates.millisOf(this.hour, this.minute, 0l, 0l);
        }

        @Override
        public Date combineWith(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, this.hour);
            calendar.set(Calendar.MINUTE, this.minute);
            return calendar.getTime();
        }

        @Override
        public String toString() {
            if (isSet()) {
                return String.format("%s:%s", decimalFormat.format(this.hour), decimalFormat.format(this.minute));
            }
            return "???";
        }
    }
}
