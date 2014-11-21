package at.wrdlbrnft.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by Xaver on 24/07/14.
 */
public class ExtrasBuilder implements Serializable {

    public static ExtrasBuilder create() {
        return new ExtrasBuilder();
    }

    public static ExtrasBuilder from(Bundle bundle) {
        ExtrasBuilder builder = new ExtrasBuilder();
        builder.copyFrom(bundle);
        return builder;
    }

    private final Map<String, AbstractExtraContainer<?>> extrasMap = Maps.newMap();

    public void applyTo(Intent target) {
        IntentTarget intentTarget = new IntentTarget(target);
        applyTo(intentTarget);
    }

    public void applyTo(Bundle target) {
        BundleTarget bundleTarget = new BundleTarget(target);
        applyTo(bundleTarget);
    }

    public void applyTo(IExtrasContainer target) {
        Set<String> keys = this.extrasMap.keySet();
        for (String key : keys) {
            AbstractExtraContainer<?> container = this.extrasMap.get(key);
            container.apply(target, key);
        }
    }

    public ExtrasBuilder putExtra(String key, String extra) {
        this.extrasMap.put(key, new StringExtra(extra));
        return this;
    }

    public ExtrasBuilder putExtra(String key, float extra) {
        this.extrasMap.put(key, new FloatExtra(extra));
        return this;
    }

    public ExtrasBuilder putExtra(String key, double extra) {
        this.extrasMap.put(key, new DoubleExtra(extra));
        return this;
    }

    public ExtrasBuilder putExtra(String key, int extra) {
        this.extrasMap.put(key, new IntegerExtra(extra));
        return this;
    }

    public ExtrasBuilder putExtra(String key, long extra) {
        this.extrasMap.put(key, new LongExtra(extra));
        return this;
    }

    public ExtrasBuilder putExtra(String key, String[] extra) {
        this.extrasMap.put(key, new StringArrayExtra(extra));
        return this;
    }

    public ExtrasBuilder putExtra(String key, Serializable extra) {
        this.extrasMap.put(key, new SerializableExtra(extra));
        return this;
    }

    public void clear() {
        this.extrasMap.clear();
    }

    private abstract class AbstractExtraContainer<T> implements Serializable {
        private final T extra;

        public AbstractExtraContainer(T extra) {
            this.extra = extra;
        }

        public void apply(IExtrasContainer container, String key) {
            apply(container, key, this.extra);
        }

        protected abstract void apply(IExtrasContainer container, String key, T extra);
    }

    private class StringExtra extends AbstractExtraContainer<String> {

        public StringExtra(String extra) {
            super(extra);
        }

        @Override
        protected void apply(IExtrasContainer container, String key, String extra) {
            container.putExtra(key, extra);
        }
    }

    private class FloatExtra extends AbstractExtraContainer<Float> {

        public FloatExtra(float extra) {
            super(extra);
        }

        @Override
        protected void apply(IExtrasContainer container, String key, Float extra) {
            container.putExtra(key, extra);
        }
    }

    private class DoubleExtra extends AbstractExtraContainer<Double> {

        public DoubleExtra(double extra) {
            super(extra);
        }

        @Override
        protected void apply(IExtrasContainer container, String key, Double extra) {
            container.putExtra(key, extra);
        }
    }

    private class IntegerExtra extends AbstractExtraContainer<Integer> {

        public IntegerExtra(int extra) {
            super(extra);
        }

        @Override
        protected void apply(IExtrasContainer container, String key, Integer extra) {
            container.putExtra(key, extra);
        }
    }

    private class LongExtra extends AbstractExtraContainer<Long> {

        public LongExtra(long extra) {
            super(extra);
        }

        @Override
        protected void apply(IExtrasContainer container, String key, Long extra) {
            container.putExtra(key, extra);
        }
    }

    private class StringArrayExtra extends AbstractExtraContainer<String[]> {

        public StringArrayExtra(String[] extra) {
            super(extra);
        }

        @Override
        protected void apply(IExtrasContainer container, String key, String[] extra) {
            container.putExtra(key, extra);
        }
    }

    private class SerializableExtra extends AbstractExtraContainer<Serializable> {

        public SerializableExtra(Serializable extra) {
            super(extra);
        }

        @Override
        protected void apply(IExtrasContainer container, String key, Serializable extra) {
            container.putExtra(key, extra);
        }
    }

    private class IntentTarget implements IExtrasContainer {

        private final Intent intent;

        private IntentTarget(Intent intent) {
            this.intent = intent;
        }

        @Override
        public void putExtra(String key, String extra) {
            this.intent.putExtra(key, extra);
        }

        @Override
        public void putExtra(String key, float extra) {
            this.intent.putExtra(key, extra);
        }

        @Override
        public void putExtra(String key, double extra) {
            this.intent.putExtra(key, extra);
        }

        @Override
        public void putExtra(String key, int extra) {
            this.intent.putExtra(key, extra);
        }

        @Override
        public void putExtra(String key, long extra) {
            this.intent.putExtra(key, extra);
        }

        @Override
        public void putExtra(String key, Bundle extra) {
            this.intent.putExtra(key, extra);
        }

        @Override
        public void putExtra(String key, String[] extra) {
            this.intent.putExtra(key, extra);
        }

        @Override
        public void putExtra(String key, Serializable extra) {
            this.intent.putExtra(key, extra);
        }
    }

    private class BundleTarget implements IExtrasContainer {

        private final Bundle bundle;

        private BundleTarget(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public void putExtra(String key, String extra) {
            this.bundle.putString(key, extra);
        }

        @Override
        public void putExtra(String key, float extra) {
            this.bundle.putFloat(key, extra);
        }

        @Override
        public void putExtra(String key, double extra) {
            this.bundle.putDouble(key, extra);
        }

        @Override
        public void putExtra(String key, int extra) {
            this.bundle.putInt(key, extra);
        }

        @Override
        public void putExtra(String key, long extra) {
            this.bundle.putLong(key, extra);
        }

        @Override
        public void putExtra(String key, Bundle extra) {
            this.bundle.putBundle(key, extra);
        }

        @Override
        public void putExtra(String key, String[] extra) {
            this.bundle.putStringArray(key, extra);
        }

        @Override
        public void putExtra(String key, Serializable extra) {
            this.bundle.putSerializable(key, extra);
        }
    }

    public Bundle toBundle() {
        final Bundle bundle = new Bundle();
        applyTo(bundle);
        return bundle;
    }

    public Intent toIntent(String action) {
        final Intent intent = new Intent(action);
        applyTo(intent);
        return intent;
    }

    public Intent toIntent(Context context, Class<?> target) {
        final Intent intent = new Intent(context, target);
        applyTo(intent);
        return intent;
    }

    public Intent toIntent(Context context, Class<?> target, String action) {
        final Intent intent = new Intent(context, target);
        intent.setAction(action);
        applyTo(intent);
        return intent;
    }

    public void copyFrom(Bundle bundle) {
        Set<String> keys = bundle.keySet();
        for (String key : keys) {
            Object item = bundle.get(key);
            if (item instanceof String) {
                putExtra(key, (String) item);
            } else if (item instanceof Float) {
                putExtra(key, (Float) item);
            } else if (item instanceof Double) {
                putExtra(key, (Double) item);
            } else if (item instanceof Integer) {
                putExtra(key, (Integer) item);
            } else if (item instanceof Long) {
                putExtra(key, (Long) item);
            } else if (item instanceof String[]) {
                putExtra(key, (String[]) item);
            } else if (item instanceof Serializable) {
                putExtra(key, (Serializable) item);
            } else if (item != null) {
                throw new IllegalStateException(String.format("Encountered unexpected class when copying from bundle: %s", item.getClass().getName()));
            } else {
                throw new IllegalStateException(String.format("Encountered a null object in the Bundle!"));
            }
        }
    }

    private interface IExtrasContainer extends Serializable {
        public void putExtra(String key, String extra);

        public void putExtra(String key, float extra);

        public void putExtra(String key, double extra);

        public void putExtra(String key, int extra);

        public void putExtra(String key, long extra);

        public void putExtra(String key, Bundle extra);

        public void putExtra(String key, String[] extra);

        public void putExtra(String key, Serializable extra);
    }
}