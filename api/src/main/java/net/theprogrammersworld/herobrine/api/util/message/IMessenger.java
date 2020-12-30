package net.theprogrammersworld.herobrine.api.util.message;

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
     * Logs formatted message with log level and throwable information to console and log file
     * <p>
     * After the message information about the throwable such name, message and stack trace will be printed
     *
     * @param level     level to write the message with
     * @param msg       the message as plain text itself
     * @param throwable throwable that information will be logged
     * @param args      optional args that can replace placeholders in the message
     * @see MessageLevel
     */
    void logThrowable(MessageLevel level, String msg, Throwable throwable, Object... args);

    /**
     * Logs message with additional information about a catched exception
     *
     * @param level     level to write the message with
     * @param msg       the message as plain text itself
     * @param throwable throwable that information will be logged
     * @param args      optional args that can replace placeholders in the message
     * @see #logThrowable(MessageLevel, String, Throwable, Object...)
     */
    void catching(MessageLevel level, String msg, Throwable throwable, Object... args);

    /**
     * Logs message with additional information about a catched exception. The message level is {@link MessageLevel#ERROR}
     *
     * @param msg       the message as plain text itself
     * @param throwable throwable that information will be logged
     * @param args      optional args that can replace placeholders in the message
     * @see #logThrowable(MessageLevel, String, Throwable, Object...)
     */
    void catching(String msg, Throwable throwable, Object... args);

    /**
     * Logs a message that will be throw after logging.
     *
     * @param level     level level to write the message with
     * @param msg       the message as plain text itself
     * @param throwable throwable that information will be logged
     * @param args      optional args that can replace placeholders in the message
     * @param <T>       type of throwable
     * @return the throwable instance
     * @see #logThrowable(MessageLevel, String, Throwable, Object...)
     */
    <T extends Throwable> T throwing(MessageLevel level, String msg, T throwable, Object... args);

    /**
     * Logs a message that will be throw after logging. The message level is {@link MessageLevel#ERROR}
     *
     * @param msg       the message as plain text itself
     * @param throwable throwable that information will be logged
     * @param args      optional args that can replace placeholders in the message
     * @param <T>       type of throwable
     * @return the throwable instance
     * @see #logThrowable(MessageLevel, String, Throwable, Object...)
     */
    <T extends Throwable> T throwing(String msg, T throwable, Object... args);

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
     * Returns true if a message with the specified level will be logged
     *
     * @param level level the message would have
     * @return Return true if message will be logged
     * @see MessageLevel
     */
    boolean isLevel(MessageLevel level);

    /**
     * Returns true if messages with {@link MessageLevel#FATAL} message level will be logged
     *
     * @return Return true if message will be logged
     * @see MessageLevel
     * @see #isLevel(MessageLevel)
     */
    boolean isFatal();

    /**
     * Returns true if messages with {@link MessageLevel#ERROR} message level will be logged
     *
     * @return Return true if message will be logged
     * @see MessageLevel
     * @see #isLevel(MessageLevel)
     */
    boolean isError();

    /**
     * Returns true if messages with {@link MessageLevel#WARN} message level will be logged
     *
     * @return Return true if message will be logged
     * @see MessageLevel
     * @see #isLevel(MessageLevel)
     */
    boolean isWarn();

    /**
     * Returns true if messages with {@link MessageLevel#INFO} message level will be logged
     *
     * @return Return true if message will be logged
     * @see MessageLevel
     * @see #isLevel(MessageLevel)
     */
    boolean isInfo();

    /**
     * Returns true if messages with {@link MessageLevel#DEBUG} message level will be logged
     *
     * @return Return true if message will be logged
     * @see MessageLevel
     * @see #isLevel(MessageLevel)
     */
    boolean isDebug();

    /**
     * Returns true if messages with {@link MessageLevel#TRACE} message level will be logged
     *
     * @return Return true if message will be logged
     * @see MessageLevel
     * @see #isLevel(MessageLevel)
     */
    boolean isTrace();

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
     * Get name of the logger. This will be printed before the message. [name]
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
