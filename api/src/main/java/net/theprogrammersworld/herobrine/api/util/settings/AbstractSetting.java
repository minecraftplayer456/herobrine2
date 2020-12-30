package net.theprogrammersworld.herobrine.api.util.settings;

import net.theprogrammersworld.herobrine.api.util.storage.IDataKey;

import java.util.List;
import java.util.Map;

public abstract class AbstractSetting<T> implements ISetting<T> {
    protected final String path;
    protected T value;

    public AbstractSetting(String path, T value) {
        this.path = path;
        this.value = value;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public T getValue() {
        return value;
    }

    public static class BooleanSetting extends AbstractSetting<Boolean> {
        public BooleanSetting(String path, boolean value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getBoolean(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setBoolean(path, value);
        }
    }

    public static class DoubleSetting extends AbstractSetting<Double> {
        public DoubleSetting(String path, double value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getDouble(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setDouble(path, value);
        }
    }

    public static class FloatSetting extends AbstractSetting<Float> {
        public FloatSetting(String path, float value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getFloat(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setFloat(path, value);
        }
    }

    public static class IntSetting extends AbstractSetting<Integer> {
        public IntSetting(String path, int value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getInt(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setInt(path, value);
        }
    }

    public static class LongSetting extends AbstractSetting<Long> {
        public LongSetting(String path, long value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getLong(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setLong(path, value);
        }
    }

    public static class StringSetting extends AbstractSetting<String> {
        public StringSetting(String path, String value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getString(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setString(path, value);
        }
    }

    public static class Setting<T> extends AbstractSetting<T> {
        public Setting(String path, T value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getObjectUnchecked(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setObject(path, value);
        }
    }

    public static class ListSetting<T> extends AbstractSetting<List<T>> {
        public ListSetting(String path, List<T> value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getList(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setList(path, value);
        }
    }

    public static class MapSetting<K, V> extends AbstractSetting<Map<K, V>> {
        public MapSetting(String path, Map<K, V> value) {
            super(path, value);
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getMap(path);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setMap(path, value);
        }
    }

    public static class EnumSetting<T extends Enum<T>> extends AbstractSetting<T> {
        private final Class<T> clazz;

        public EnumSetting(String path, T value, Class<T> clazz) {
            super(path, value);
            this.clazz = clazz;
        }

        @Override
        public void loadKey(IDataKey root) {
            value = root.getEnum(path, clazz);
        }

        @Override
        public void saveKey(IDataKey root) {
            root.setEnum(path, value);
        }
    }
}
