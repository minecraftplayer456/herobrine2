package net.theprogrammersworld.herobrine.api.util;

/**
 * Enum that represents the different levels a message can be send.
 * <p>
 * The logger has a level set too. So to log something his level must be lower than the level of the message to log
 *
 * @author MinecraftPlayer456
 * @version 0.1.0
 * @see IMessenger
 * @since 2.1.2
 */
public enum MessageLevel {
    /**
     * All messages will be logged
     */
    ALL(0, "all"),

    /**
     * All messages will be logged
     * <p>
     * Trace meaning: Messages, which will full spam the log
     */
    TRACE(1, "trace"),

    /**
     * All messages will be logged except trace messages
     * <p>
     * Debug meaning: Messages, which will help to understand the problem, but are not normal information for a normal no errorring environment
     */
    DEBUG(2, "debug"),

    /**
     * All messages will be logged except trace and debug messages
     * <p>
     * Info meaning: Messages, which will tell something normal about the plugin
     */
    INFO(3, "info"),

    /**
     * Only warn, error and fatal messages will be logged
     * <p>
     * Warn meaning: Something happened that the user shouzld know
     */
    WARN(4, "warn"),

    /**
     * Only error and fatal messages will be logged
     * <p>
     * Error meaning: An failure happened
     */
    ERROR(5, "error"),

    /**
     * Only fatal messages will be logger
     * <p>
     * Fatal meaning: An fatal failure happened the plugin needs to shutdown
     */
    FATAL(6, "fatal"),

    /**
     * None messages will be logged
     */
    NONE(Integer.MAX_VALUE, "none");

    private final int level;
    private final String name;

    MessageLevel(int level, String name) {
        this.level = level;
        this.name = name;
    }

    /**
     * Return the enum constant that have the same internal name
     *
     * @param name The name of the enum constant to be returned
     * @return the enum constant with that name
     */
    public static MessageLevel valueOfName(String name) {
        for (final MessageLevel level : values()) {
            if (level.name.equals(name)) {
                return level;
            }
        }
        throw new EnumConstantNotPresentException(MessageLevel.class, name);
    }

    /**
     * Return the int level of the log level.
     * <p>
     * This is need for comprehension of the different levels
     *
     * @return The int level of the log level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Return name of the log level
     *
     * @return The name of the log level
     */
    public String getString() {
        return name;
    }
}
