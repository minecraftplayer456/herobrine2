package net.theprogrammersworld.herobrine.api.util;

//TODO Translating
//TODO Ingame chat messaging, command response

/**
 * Own logging implementation with default log4j2 backend.
 * <p>
 * Helps with printing to ingame chat or console
 *
 * @author MinecraftPlayer456
 * @version 0.1.0
 * @see MessageLevel
 * @since 2.1.2
 */
public interface IMessenger {

    /**
     * Logs formatted message with specified log level to console and log file.
     * <p>
     * Placeholders in the message will be replaced with args
     * The logger will only log if the log level of the message higher or equal to the log level of the messenger
     *
     * @param level level to write the message with
     * @param msg   the message as plain text itself
     * @param args  optional args that can replace placeholders in the message
     * @see MessageLevel
     */
    void log(MessageLevel level, String msg, Object... args);

    /**
     * Logs an formatted message with fatal level
     *
     * @param msg  the message as plain text itself
     * @param args optional args that can replace placeholders in the message
     * @see #log(MessageLevel, String, Object...)
     */
    void fatal(String msg, Object... args);

    /**
     * Logs an formatted message with error level
     *
     * @param msg  the message as plain text itself
     * @param args optional args that can replace placeholders in the message
     * @see #log(MessageLevel, String, Object...)
     */
    void error(String msg, Object... args);

    /**
     * Logs an formatted message with warn level
     *
     * @param msg  the message as plain text itself
     * @param args optional args that can replace placeholders in the message
     * @see #log(MessageLevel, String, Object...)
     */
    void warn(String msg, Object... args);

    /**
     * Logs an formatted message with info level
     *
     * @param msg  the message as plain text itself
     * @param args optional args that can replace placeholders in the message
     * @see #log(MessageLevel, String, Object...)
     */
    void info(String msg, Object... args);

    /**
     * Logs an formatted message with debug level
     *
     * @param msg  the message as plain text itself
     * @param args optional args that can replace placeholders in the message
     * @see #log(MessageLevel, String, Object...)
     */
    void debug(String msg, Object... args);

    /**
     * Logs an formatted message with trace level
     *
     * @param msg  the message as plain text itself
     * @param args optional args that can replace placeholders in the message
     * @see #log(MessageLevel, String, Object...)
     */
    void trace(String msg, Object... args);

    /**
     * Gets current log level of the messenger
     *
     * @return current log level of messenger
     * @see MessageLevel
     */
    MessageLevel getLevel();

    /**
     * Set log level of messenger. Normally this is be set with the value from the config file
     *
     * @param level The level for the logger
     * @see MessageLevel
     */
    void setLevel(MessageLevel level);

    /**
     * Get name of the logger. This will be printed befor the message. [name]
     *
     * @return name of the logger
     */
    String getName();

    /**
     * Creates a child messenger for example for an extension plugin for herobrine
     *
     * @param name The name of the children messenger
     * @return Newly created children messenger
     */
    IMessenger createChild(String name);
}
