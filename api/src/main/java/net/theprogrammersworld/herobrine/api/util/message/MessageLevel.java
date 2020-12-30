package net.theprogrammersworld.herobrine.api.util.message;

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
    ALL(0),

    /**
     * All messages will be logged
     * <p>
     * Trace meaning: Messages, which will full spam the log
     */
    TRACE(1),

    /**
     * All messages will be logged except trace messages
     * <p>
     * Debug meaning: Messages, which will help to understand the problem, but are not normal information for a normal no errorring environment
     */
    DEBUG(2),

    /**
     * All messages will be logged except trace and debug messages
     * <p>
     * Info meaning: Messages, which will tell something normal about the plugin
     */
    INFO(3),

    /**
     * Only warn, error and fatal messages will be logged
     * <p>
     * Warn meaning: Something happened that the user should know
     */
    WARN(4),

    /**
     * Only error and fatal messages will be logged
     * <p>
     * Error meaning: An failure happened
     */
    ERROR(5),

    /**
     * Only fatal messages will be logger
     * <p>
     * Fatal meaning: An fatal failure happened the plugin needs to shutdown
     */
    FATAL(6),

    /**
     * None messages will be logged
     */
    NONE(Integer.MAX_VALUE);

    private final int level;

    MessageLevel(int level) {
        this.level = level;
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
}
