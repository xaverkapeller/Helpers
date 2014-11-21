package at.wrdlbrnft.helpers;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Xaver on 28/08/14.
 */
public class CanvasHelper {

    public static float setTextSizeForWidth(Paint paint, float desiredWidth, String text, float max, float min) {
        final float textSize = 12.0f;

        paint.setTextSize(textSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        float desiredTextSize = textSize * desiredWidth / bounds.width();
        if(desiredTextSize > max) {
            desiredTextSize = max;
        } else if(desiredTextSize < min) {
            desiredTextSize = min;
        }
        paint.setTextSize(desiredTextSize);
        return desiredTextSize;
    }

    private static float setTextSizeForWidth(Paint paint, float desiredWidth, String text, float max) {
        return setTextSizeForWidth(paint, desiredWidth, text, max, 0.0f);
    }

    public static float setTextSizeForWidth(Paint paint, float desiredWidth, String text) {
        return setTextSizeForWidth(paint, desiredWidth, text, Float.MAX_VALUE, 0.0f);
    }
}
