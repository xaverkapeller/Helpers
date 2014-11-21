package at.wrdlbrnft.helpers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Xaver on 26/08/14.
 */
public class Containers {

    public static Container<Context, String> string(int stringResId) {
        return new StringResourceContainer(stringResId);
    }

    public static <O extends View> Container<ViewGroup, O> view(int id) {
        return new ViewIdContainer<>(id);
    }

    public static <I, O> Container<I, O> item(final O item) {
        return new InstanceContainer<>(item);
    }

    private static class StringResourceContainer implements Container<Context, String> {

        private final int resId;

        private StringResourceContainer(int resId) {
            this.resId = resId;
        }

        @Override
        public String get(Context value) {
            return value.getString(resId);
        }
    }

    private static class ViewIdContainer<O extends View> implements Container<ViewGroup, O> {

        private final int id;

        private ViewIdContainer(int id) {
            this.id = id;
        }

        @Override
        public O get(ViewGroup value) {
            return (O) value.findViewById(this.id);
        }
    }

    private static class InstanceContainer<I, O> implements Container<I, O> {

        private final O instance;

        private InstanceContainer(O instance) {
            this.instance = instance;
        }

        @Override
        public O get(I value) {
            return this.instance;
        }
    }

}
