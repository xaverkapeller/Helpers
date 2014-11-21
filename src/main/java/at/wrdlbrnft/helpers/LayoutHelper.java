package at.wrdlbrnft.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Xaver on 27/03/14.
 */
public class LayoutHelper {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static final Object NOT_USED = new Object();

    public static class Location {
        private final int x;
        private final int y;

        private Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static Location getScreenLocation(View view) {
        if (view == null) {
            return new Location(0, 0);
        }

        final int[] location = new int[2];
        view.getLocationOnScreen(location);
        final int x = location[0];
        final int y = location[1];
        return new Location(x, y);
    }

    public static int[] calculateDelta(View first, View second) {
        final int[] sourceLocation = new int[2];
        first.getLocationOnScreen(sourceLocation);

        final int[] currentNameLocation = new int[2];
        second.getLocationOnScreen(currentNameLocation);

        final int[] delta = new int[2];
        delta[0] = currentNameLocation[0] - sourceLocation[0] + second.getPaddingLeft() - first.getPaddingLeft();
        delta[1] = currentNameLocation[1] - sourceLocation[1] + second.getPaddingTop() - first.getPaddingTop();

        return delta;
    }

    /**
     * Convenience method which combines {@link #getDimensionFromResource(android.content.Context, int)} and {@link #dpToPixel(android.content.Context, float)}
     *
     * @param context    A {@link android.content.Context} used to get the displays density factor
     * @param dimenResId The id of the dimension resource in question
     * @return Returns the equivalent amount of pixels.
     */
    public static int dpResourceToPixel(Context context, int dimenResId) {
        float dp = getDimensionFromResource(context, dimenResId);
        return dpToPixel(context, dp);
    }

    /**
     * Returns the value of a dimen resource independent of the displays density factor
     *
     * @param context    A {@link android.content.Context} used to get the displays density factor
     * @param dimenResId The id of the dimension resource in question
     * @return Returns the value of the dimension resource
     */
    public static float getDimensionFromResource(Context context, int dimenResId) {
        return context.getResources().getDimension(dimenResId) / getDisplayDensityFactor(context);
    }

    /**
     * Converts dip (density independent pixels) into pixels
     *
     * @param context A {@link android.content.Context} used to get the displays density factor
     * @param dip     The amount of dip to convert
     * @return Returns the equivalent amount of pixels.
     */
    public static int dpToPixel(Context context, float dip) {
        float scale = getDisplayDensityFactor(context);
        return (int) (dip * scale + 0.5f);
    }

    /**
     * Converts pixel into dip (density independent pixels)
     *
     * @param context A {@link android.content.Context} used to get the displays density factor
     * @param pixel   The amount of pixels to convert
     * @return Returns the equivalent amount of dip.
     */
    public static int pixelToDp(Context context, float pixel) {
        float scale = getDisplayDensityFactor(context);
        return (int) (pixel / scale + 0.5f);
    }

    /**
     * Generates a unique id for a dynamically created {@link android.view.View}
     *
     * @return a unique id
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();

            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.

            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /**
     * Uses a {@link android.content.Context} to return the density factory of the current display
     *
     * @param context The {@link android.content.Context} used to retrieve the density factor
     * @return Returns the density factor of the current display
     */
    private static float getDisplayDensityFactor(Context context) {
        if (context != null) {
            Resources res = context.getResources();
            if (res != null) {
                DisplayMetrics metrics = res.getDisplayMetrics();
                if (metrics != null) {
                    return metrics.density;
                }
            }
        }
        return 1.0f;
    }

    /**
     * Populates a {@link android.view.ViewGroup} from an {@link android.widget.Adapter}
     *
     * @param viewGroup The {@link android.view.ViewGroup} which will be populated
     * @param adapter   The {@link android.widget.Adapter} which supplies the {@link android.view.View Views} to the {@link android.view.ViewGroup}
     */
    public static void populateViewGroup(ViewGroup viewGroup, Adapter adapter) {
        if (viewGroup != null && adapter != null) {
            int count = adapter.getCount();
            for (int i = 0; i < count; i++) {
                View view = adapter.getView(i, null, viewGroup);
                viewGroup.addView(view);
            }
        }
    }

    public static View getActionBarView(FragmentActivity activity) {
        Window window = activity.getWindow();
        View v = window.getDecorView();
        int resId = activity.getResources().getIdentifier("action_bar_container", "id", "android");
        return v.findViewById(resId);
    }

    public static TextView getActionBarTitleView(Activity activity) {
        Window window = activity.getWindow();
        View view = window.getDecorView();
        final int resId = activity.getResources().getIdentifier("action_bar_title", "id", "android");
        return (TextView) view.findViewById(resId);
    }

    public static TextView getActionBarSubTitleView(Activity activity) {
        Window window = activity.getWindow();
        View view = window.getDecorView();
        final int resId = activity.getResources().getIdentifier("action_bar_subtitle", "id", "android");
        return (TextView) view.findViewById(resId);
    }

    public static void setClipChildren(ViewGroup viewGroup, boolean clipChildren) {
        viewGroup.setClipChildren(clipChildren);
        final int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                final ViewGroup group = (ViewGroup) child;
                group.setClipChildren(clipChildren);
                setClipChildren(group, clipChildren);
            }
        }
    }

    public static void setClipView(View view, boolean clip) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                viewGroup.setClipChildren(clip);
                setClipView(viewGroup, clip);
            }
        }
    }

    public static ViewGroup getRootOf(View view) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                return getRootOf(parent);
            }
            return null;
        }
        return null;
    }

    public static void populateLinearLayout(LinearLayout layout, Adapter adapter, int spacing) {
        if (layout != null && adapter != null) {
            int adapterCount = adapter.getCount();
            int layoutCount = layout.getChildCount();
            for (int i = 0; i < adapterCount || i < layoutCount; i++) {
                int itemSpacing = i > 0 ? spacing : 0;

                View convertView = null;
                if (i < layoutCount) {
                    View view = layout.getChildAt(i);
                    if (view != null) {
                        convertView = view;
                    }
                }

                if (i < adapterCount) {

                    View view = adapter.getView(i, convertView, layout);

                    if (view != null) {
                        if (layout.getOrientation() == LinearLayout.HORIZONTAL) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(itemSpacing, 0, 0, 0);
                            view.setLayoutParams(params);
                        } else if (layout.getOrientation() == LinearLayout.VERTICAL) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, itemSpacing, 0, 0);
                            view.setLayoutParams(params);
                        }
                        if (i >= layoutCount) {
                            layout.addView(view);
                        }
                    }
                } else if (convertView != null) {
                    layout.removeViewAt(i);
                }
            }
        }
    }

    /**
     * Equally distributes a fixed amount of {@link android.view.View Views} from an {@link android.widget.Adapter}
     * in a {@link android.widget.LinearLayout}. If the {@link android.widget.Adapter} cannot supply enough {@link android.view.View Views}
     * to fill the {@link android.widget.LinearLayout} then transparent empty dummy {@link android.view.View Views} will be
     * used instead. These dummy {@link android.view.View Views} will be automatically replaced if a {@link android.view.View}
     * is available by the next time {@link #distributeViews(android.widget.LinearLayout, android.widget.Adapter, int)} is called.
     *
     * @param layout    The {@link android.widget.LinearLayout} which will be populated
     * @param adapter   The {@link android.widget.Adapter} which supplies the {@link android.view.View Views} to the {@link android.widget.LinearLayout}
     * @param viewCount The amount of {@link android.view.View Views} which will be displayed in the {@link android.widget.LinearLayout}.
     */
    public static void distributeViews(LinearLayout layout, Adapter adapter, int viewCount) {
        distributeViews(layout, adapter, viewCount, 0);
    }

    /**
     * Equally distributes a fixed amount of {@link android.view.View Views} from an {@link android.widget.Adapter}
     * in a {@link android.widget.LinearLayout}. If the {@link android.widget.Adapter} cannot supply enough {@link android.view.View Views}
     * to fill the {@link android.widget.LinearLayout} then transparent empty dummy {@link android.view.View Views} will be
     * used instead. These dummy {@link android.view.View Views} will be automatically replaced if a {@link android.view.View}
     * is available by the next time {@link #distributeViews(android.widget.LinearLayout, android.widget.Adapter, int)} is called.
     *
     * @param layout    The {@link android.widget.LinearLayout} which will be populated
     * @param adapter   The {@link android.widget.Adapter} which supplies the {@link android.view.View Views} to the {@link android.widget.LinearLayout}
     * @param viewCount The amount of {@link android.view.View Views} which will be displayed in the {@link android.widget.LinearLayout}.
     * @param spacing   Specifies a margin between each {@link android.view.View} in the {@link android.widget.LinearLayout}
     */
    public static void distributeViews(LinearLayout layout, Adapter adapter, int viewCount, int spacing) {
        if (layout != null && adapter != null) {
            int adapterCount = adapter.getCount();
            int layoutCount = layout.getChildCount();
            for (int i = 0; i < viewCount; i++) {
                int itemSpacing = i > 0 ? spacing : 0;

                View convertView = null;
                if (i < layoutCount) {
                    View view = layout.getChildAt(i);
                    if (view != null && view.getTag() != NOT_USED) {
                        convertView = view;
                        convertView.setVisibility(View.VISIBLE);
                    }
                }

                View view;
                if (i < adapterCount) {
                    view = adapter.getView(i, convertView, layout);
                } else if (convertView != null) {
                    view = convertView;
                    view.setVisibility(View.INVISIBLE);
                } else {
                    view = new View(layout.getContext());
                    view.setTag(NOT_USED);
                    view.setVisibility(View.INVISIBLE);
                }

                if (view != null) {
                    if (layout.getOrientation() == LinearLayout.HORIZONTAL) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                        params.setMargins(itemSpacing, 0, 0, 0);
                        view.setLayoutParams(params);
                    } else if (layout.getOrientation() == LinearLayout.VERTICAL) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                        params.setMargins(0, itemSpacing, 0, 0);
                        view.setLayoutParams(params);
                    }
                    if (i >= layoutCount) {
                        layout.addView(view);
                    }
                }
            }
        }
    }

    public static int getTopRelativeToRootView(View view) {
        if (view != null) {
            if (view.getParent() == view.getRootView()) {
                return view.getTop();
            } else {
                return view.getTop() + getTopRelativeToRootView((View) view.getParent());
            }
        }
        return 0;
    }

    public static int getLeftRelativeToRootView(View view) {
        if (view != null) {
            if (view.getParent() == view.getRootView()) {
                return view.getLeft();
            } else {
                return view.getLeft() + getLeftRelativeToRootView((View) view.getParent());
            }
        }
        return 0;
    }

    public static int getRightRelativeToRootView(View view) {
        if (view != null) {
            if (view.getParent() == view.getRootView()) {
                return view.getRight();
            } else {
                return view.getRight() + getRightRelativeToRootView((View) view.getParent());
            }
        }
        return 0;
    }

    public static int getBottomRelativeToRootView(View view) {
        if (view != null) {
            if (view.getParent() == view.getRootView()) {
                return view.getBottom();
            } else {
                return view.getBottom() + getBottomRelativeToRootView((View) view.getParent());
            }
        }
        return 0;
    }
}
