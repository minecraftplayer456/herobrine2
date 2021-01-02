package net.theprogrammersworld.herobrine.api.util.storage;

import java.util.List;
import java.util.Map;

import net.theprogrammersworld.herobrine.api.HerobrineApi;

public abstract class AbstractDataKey implements IDataKey {
    protected final String name;
    protected final String path;

    public AbstractDataKey(String path) {
        this.path = path;

        int last = path.lastIndexOf('.');
        name = last == -1 ? path : path.substring(last + 1);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof Boolean) {
                return (Boolean) value;
            }

            if (value instanceof String) {
                String raw = (String) value;
                return raw.equalsIgnoreCase("true") || raw.equalsIgnoreCase("yes");
            }
        } else {
            setBoolean(key, def);
        }
        return def;
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0D);
    }

    @Override
    public double getDouble(String key, double def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            }

            if (value instanceof String) {
                String raw = (String) value;
                if (!raw.isEmpty()) {
                    return Double.parseDouble(raw);
                }
            }
        } else {
            setDouble(key, def);
        }
        return def;
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, 0F);
    }

    @Override
    public float getFloat(String key, float def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof Number) {
                return ((Number) value).floatValue();
            }

            if (value instanceof String) {
                String raw = (String) value;
                if (!raw.isEmpty()) {
                    return Float.parseFloat(raw);
                }
            }
        } else {
            setFloat(key, def);
        }
        return def;
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public int getInt(String key, int def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof Number) {
                return ((Number) value).intValue();
            }

            if (value instanceof String) {
                String raw = (String) value;
                if (!raw.isEmpty()) {
                    return Integer.parseInt(raw);
                }
            }
        } else {
            setInt(key, def);
        }
        return def;
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0L);
    }

    @Override
    public long getLong(String key, long def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof Number) {
                return ((Number) value).longValue();
            }

            if (value instanceof String) {
                String raw = (String) value;
                if (!raw.isEmpty()) {
                    return Long.parseLong(raw);
                }
            }
        } else {
            setLong(key, def);
        }
        return def;
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof String) {
                return (String) value;
            }
        } else {
            setString(key, def);
        }
        return def;
    }

    @Override
    public Object getObject(String key, Object def) {
        if (keyExists(key)) {
            Object value = getObject(key);
            if (value != null) {
                return value;
            }
        } else {
            setObject(key, def);
        }
        return def;
    }

    @Override
    public <T> T getObjectUnchecked(String key) {
        return getObjectUnchecked(key, null);
    }

    @Override
    public <T> T getObjectUnchecked(String key, T def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value != null) {
                T cast = unsafeCast(value);
                if (cast != null) {
                    return cast;
                }
            }
        } else {
            setObject(key, def);
        }
        return def;
    }

    @Override
    public <T> List<T> getList(String key) {
        return getList(key, null);
    }

    @Override
    public <T> List<T> getList(String key, List<T> def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof List) {
                List<T> list = unsafeCast(value);
                if (list != null) {
                    return list;
                }
            }
        } else {
            setList(key, def);
        }
        return def;
    }

    @Override
    public <K, V> Map<K, V> getMap(String key) {
        return getMap(key, null);
    }

    @Override
    public <K, V> Map<K, V> getMap(String key, Map<K, V> def) {
        if (keyExists(key)) {
            Object value = getObject(key);

            if (value instanceof Map) {
                Map<K, V> cast = unsafeCast(value);
                if (cast != null) {
                    return cast;
                }
            }
        } else {
            setMap(key, def);
        }
        return def;
    }

    @Override
    public <T extends Enum<T>> T getEnum(String key, Class<T> clazz) {
        return getEnum(key, null, clazz);
    }

    @Override
    public <T extends Enum<T>> T getEnum(String key, T def, Class<T> clazz) {
        if (keyExists(key)) {
            Object value = getObject(key);
            if (value instanceof String) {
                String raw = (String) value;
                if (!raw.isEmpty()) {
                    try {
                        return Enum.valueOf(clazz, raw);
                    } catch (IllegalArgumentException e) {
                        HerobrineApi.getMessenger().catching("Could not find enum value {} in enum {}", e, raw, clazz.getName());
                        return null;
                    }
                }
            }
        } else {
            setEnum(key, def);
        }
        return def;
    }

    @Override
    public void setBoolean(String key, boolean value) {
        setObject(key, value);
    }

    @Override
    public void setDouble(String key, double value) {
        setObject(key, value);
    }

    @Override
    public void setFloat(String key, float value) {
        setObject(key, value);
    }

    @Override
    public void setInt(String key, int value) {
        setObject(key, value);
    }

    @Override
    public void setLong(String key, long value) {
        setObject(key, value);
    }

    @Override
    public void setString(String key, String value) {
        setObject(key, value);
    }

    @Override
    public void setList(String key, List<?> value) {
        setObject(key, value);
    }

    @Override
    public void setMap(String key, Map<?, ?> value) {
        setObject(key, value);
    }

    @Override
    public void setEnum(String key, Enum<?> value) {
        setObject(key, value.name());
    }

    @Override
    public boolean keyExists() {
        return keyExists("");
    }

    @Override
    public String getRelativeKey(String key) {
        if (key.isEmpty()) {
            return path;
        }

        if (key.charAt(0) == '.') {
            return path.isEmpty() ? key.substring(1) : path + key;
        }

        return path.isEmpty() ? key : path + "." + key;
    }

    protected abstract IDataKey createRelative(String key);

    @Override
    public IDataKey getRelative(String key) {
        if (key == null || key.isEmpty()) {
            return this;
        }

        return createRelative(key);
    }

    @SuppressWarnings("unchecked")
    protected <T> T unsafeCast(Object object) {
        try {
            return (T) object;
        } catch (ClassCastException e) {
            HerobrineApi.getMessenger().catching("Could not cast data key object: {}", e, object.getClass().getName());
            return null;
        }
    }
}
