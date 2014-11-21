package at.wrdlbrnft.helpers.time;

import java.util.Date;

/**
 * Created by Xaver on 21/07/14.
 */
public interface Time {
    public int getHour();
    public void setHour(int hour);
    public int getMinute();
    public void setMinute(int minute);
    public boolean isSet();
    public long toMillis();
    public Date combineWith(Date date);
}
