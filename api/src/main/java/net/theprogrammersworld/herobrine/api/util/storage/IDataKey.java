package net.theprogrammersworld.herobrine.api.util.storage;

import java.util.List;
import java.util.Map;

/**
 * Data container that hold subkeys and data like objects, strings, ints
 * <p>
 * Comparable with {@link org.bukkit.configuration.ConfigurationSection}
 *
 * @author MinecraftPlayer456
 * @version 0.1.0
 * @since 2.1.2
 */
public interface IDataKey {

    /**
     * Returns the name of the IDataKey
     * <p>
     * The name of the IDataKey is the last part separated by . in the path. ({@link #getPath()})
     *
     * @return name of IDataKey
     * @see #getPath()
     */
    String getName();

    /**
     * Returns the path to the current IDataKey
     *
     * @return The path of current IDataKey
     */
    String getPath();

    /**
     * Gets the {@code boolean} value specified by given the key.
     * <p>
     * This method calls {@link #getBoolean(String, boolean)} with {@code false} as default value.
     * So if the key does not exist it will be stored with the value {@code false}.
     * The value can be a {@link String} or a {@code boolean}.
     * If the value is a string, "yes" and "true" corresponds to {@code true} and everything else corresponds to {@code false}.
     * If nothing matches false will be returned.
     *
     * @param key The key for the specified {@code boolean} value.
     * @return The {@code boolean} value specified by the key.
     * @see #getBoolean(String, boolean)
     * @see #getObject(String)
     */
    boolean getBoolean(String key);

    /**
     * Gets the {@code boolean} value  or a default value specified by the given key.
     * <p>
     * If the key does not exist it will be stored with the default value.
     * The value can be a {@link String} or a {@code boolean}.
     * If the value is a string, "yes" and "true" corresponds to true and everything else corresponds to false.
     * If nothing matches the default value will be returned.
     *
     * @param key The key for the specified {@code boolean} value.
     * @param def The default value, that will be returned if the key does not exist or the value is invalid.
     * @return The {@code boolean} value for the given key or the default value if the key does not exist or the value is invalid.
     * @see #getBoolean(String)
     * @see #getObject(String)
     */
    boolean getBoolean(String key, boolean def);

    /**
     * Gets the {@code double} value specified by the given key.
     * <p>
     * This method calls {@link #getDouble(String, double)} with {@code 0D} as default value.
     * So if the key does not exist it will be stored with the value {@code 0D}.
     * The value can be a {@link String} or a number.
     * If the value is a number, it will be converted into a {@code double}.
     * If the value is a valid not empty number {@link String}, it will be parsed as a {@code double}.
     * If nothing matches, {@code 0D} will be returned.
     *
     * @param key The key for the specified {@code double} value.
     * @return The {@code double} value for the given key, or {@code 0D} if the key does not exist or the value is invalid.
     * @see #getDouble(String, double)
     * @see #getObject(String)
     **/
    double getDouble(String key);

    /**
     * Gets the {@code double} value specified by the given key,
     * <p>
     * If the key does not exist it will be stored with the default value.
     * The value can be a {@link String} or a number.
     * If the value is a number, it will be converted into a {@code double}.
     * If the value is a valid not empty number {@link String}, it will be parsed as a {@code double}.
     * If nothing matches, the default value will be returned.
     *
     * @param key The key for the specified {@code double} value.
     * @param def The default value, that will be returned if the key does not exist or if the value is invalid.
     * @return The {@code double} value for the given key, or the default value if the key does not exist or the value is invalid.
     * @see #getDouble(String)
     * @see #getObject(String)
     */
    double getDouble(String key, double def);

    /**
     * Gets the {@code float} value specified by the given key.
     * <p>
     * If the key does not exist it will be stored with {@code 0F}.
     * The value can be a {@link String} or a number.
     * If the value is a number, it will be converted into {@code float}.
     * If the value is a valid not empty number {@link String}, it will be parsed as a {@code float}.
     * If nothing matches, {@code 0F} will be returned.
     *
     * @param key The key for the specified {@code float} value.
     * @return The {@code float} value for the given key, or {@code 0F} if the key does not exist or the value is invalid.
     * @see #getFloat(String, float)
     * @see #getObject(String)
     */
    float getFloat(String key);

    /**
     * Gets the {@code float} value specified by the given key,
     * <p>
     * If the key does not exist it will be stored with the default value.
     * The value can be a {@link String} or a number.
     * If the value is a number, it will be converted into a {@code float}.
     * If the value is a valid not empty number {@link String}, it will be parsed as a {@code float}.
     * If nothing matches, the default value will be returned.
     *
     * @param key The key for the specified {@code float} value.
     * @param def The default value, that will be returned if the key does not exist or if the value is invalid.
     * @return The {@code float} value for the given key, or the default value if the key does not exist or the value is invalid.
     * @see #getFloat(String)
     * @see #getObject(String)
     */
    float getFloat(String key, float def);

    /**
     * Gets the {@code int} value specified by the given key.
     * <p>
     * If the key does not exist it will be stored with {@code 0}.
     * The value can be a {@link String} or a number.
     * If the value is a number, it will be converted into {@code int}.
     * If the value is a valid not empty number {@link String}, it will be parsed as a {@code int}.
     * If nothing matches, {@code 0} will be returned.
     *
     * @param key The key for the specified {@code int} value.
     * @return The {@code int} value for the given key, or {@code 0} if the key does not exist or the value is invalid.
     * @see #getInt(String, int)
     * @see #getObject(String)
     */
    int getInt(String key);

    /**
     * Gets the {@code int} value specified by the given key,
     * <p>
     * If the key does not exist it will be stored with the default value.
     * The value can be a {@link String} or a number.
     * If the value is a number, it will be converted into a {@code int}.
     * If the value is a valid not empty number {@link String}, it will be parsed as a {@code int}.
     * If nothing matches, the default value will be returned.
     *
     * @param key The key for the specified {@code int} value.
     * @param def The default value, that will be returned if the key does not exist or if the value is invalid.
     * @return The {@code int} value for the given key, or the default value if the key does not exist or the value is invalid.
     * @see #getInt(String)
     * @see #getObject(String)
     */
    int getInt(String key, int def);

    long getLong(String key);

    long getLong(String key, long def);

    String getString(String key);

    String getString(String key, String def);

    Object getObject(String key);

    Object getObject(String key, Object def);

    <T> T getObjectUnchecked(String key);

    <T> T getObjectUnchecked(String key, T def);

    <T> List<T> getList(String key);

    <T> List<T> getList(String key, List<T> def);

    <K, V> Map<K, V> getMap(String key);

    <K, V> Map<K, V> getMap(String key, Map<K, V> def);

    <T extends Enum<T>> T getEnum(String key, Class<T> clazz);

    <T extends Enum<T>> T getEnum(String key, T def, Class<T> clazz);

    void setBoolean(String key, boolean value);

    void setDouble(String key, double value);

    void setFloat(String key, float value);

    void setInt(String key, int value);

    void setLong(String key, long value);

    void setString(String key, String value);

    void setObject(String key, Object value);

    void setList(String key, List<?> value);

    void setMap(String key, Map<?, ?> value);

    void setEnum(String key, Enum<?> value);

    boolean keyExists();

    boolean keyExists(String key);

    void removeKey(String key);

    IDataKey getRelative(String key);

    String getRelativeKey(String key);

    List<IDataKey> getSubKeys();

    Map<String, Object> getValues();
}
