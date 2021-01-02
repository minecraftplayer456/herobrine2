package net.theprogrammersworld.herobrine.util.message;

import net.theprogrammersworld.herobrine.api.util.message.IMessenger;
import net.theprogrammersworld.herobrine.api.util.message.MessageLevel;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class Messenger implements IMessenger {
    private final String name;
    private final Logger logger;

    private MessageLevel level = MessageLevel.INFO;

    public Messenger(String name) {
        this.name = name;
        this.logger = LogManager.getLogger(name);
    }

    @Override
    public void log(MessageLevel level, String msg, Object... args) {
        //If level is equal or higher than level of logger, message will be logged
        if (isLevel(level)) {
            logger.log(getLog4JLevel(level), formatMessage(msg), args);
        }
    }

    @Override
    public void logThrowable(MessageLevel level, String msg, Throwable throwable, Object... args) {
        if (isLevel(level)) {
            logger.log(getLog4JLevel(level), formatMessage(msg), throwable, args);
        }
    }

    @Override
    public void catching(MessageLevel level, String msg, Throwable throwable, Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append(msg);
        builder.append("\n");
        builder.append("Catching: ");
        builder.append("\n");
        logThrowable(level, builder.toString(), throwable, args);
    }

    @Override
    public void catching(String msg, Throwable throwable, Object... args) {
        catching(MessageLevel.ERROR, msg, throwable, args);
    }

    @Override
    public <T extends Throwable> T throwing(MessageLevel level, String msg, T throwable, Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append(msg);
        builder.append("\n");
        builder.append("Catching: ");
        builder.append("\n");
        logThrowable(level, builder.toString(), throwable, args);
        return throwable;
    }

    @Override
    public <T extends Throwable> T throwing(String msg, T throwable, Object... args) {
        return throwing(MessageLevel.ERROR, msg, throwable, args);
    }

    @Override
    public void fatal(String msg, Object... args) {
        log(MessageLevel.FATAL, msg, args);
    }

    @Override
    public void error(String msg, Object... args) {
        log(MessageLevel.ERROR, msg, args);
    }

    @Override
    public void warn(String msg, Object... args) {
        log(MessageLevel.WARN, msg, args);
    }

    @Override
    public void info(String msg, Object... args) {
        log(MessageLevel.INFO, msg, args);
    }

    @Override
    public void debug(String msg, Object... args) {
        log(MessageLevel.DEBUG, msg, args);
    }

    @Override
    public void trace(String msg, Object... args) {
        log(MessageLevel.TRACE, msg, args);
    }

    @Override
    public boolean isLevel(MessageLevel level) {
        return level.getLevel() >= this.level.getLevel();
    }

    @Override
    public boolean isFatal() {
        return isLevel(MessageLevel.FATAL);
    }

    @Override
    public boolean isError() {
        return isLevel(MessageLevel.ERROR);
    }

    @Override
    public boolean isWarn() {
        return isLevel(MessageLevel.WARN);
    }

    @Override
    public boolean isInfo() {
        return isLevel(MessageLevel.INFO);
    }

    @Override
    public boolean isDebug() {
        return isLevel(MessageLevel.DEBUG);
    }

    @Override
    public boolean isTrace() {
        return isLevel(MessageLevel.TRACE);
    }


    @Override
    public MessageLevel getLevel() {
        return level;
    }

    @Override
    public void setLevel(MessageLevel level) {
        this.level = level;

        Level log4JLevel = getLog4JLevel(level);

        //Set logger appender level
        LoggerContext context = LoggerContext.getContext(false);
        Configuration configuration = context.getConfiguration();
        LoggerConfig config = configuration.getLoggerConfig(logger.getName());
        config.setLevel(log4JLevel);

        //Set sysout appender level
        LoggerConfig rootConfig = configuration.getRootLogger();
        rootConfig.getAppenders().values().stream()
                .filter(appender -> appender.getName().equals("ServerGuiConsole") || appender.getName().equals("TerminalConsole"))
                .forEach(appender -> {
                    rootConfig.removeAppender(appender.getName());
                    rootConfig.addAppender(appender, null, null);
                });

        //Update logger configurations
        context.updateLoggers();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IMessenger createChild(String name) {
        return new Messenger(name);
    }

    private Level getLog4JLevel(MessageLevel level) {
        switch (level) {
            case NONE:
                return Level.OFF;
            case TRACE:
                return Level.TRACE;
            case DEBUG:
                return Level.DEBUG;
            case INFO:
                return Level.INFO;
            case WARN:
                return Level.WARN;
            case ERROR:
                return Level.ERROR;
            case FATAL:
                return Level.FATAL;
        }
        //Should never reach
        return null;
    }

    private String formatMessage(String msg) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(name);
        builder.append("] ");
        builder.append(msg);
        return builder.toString();
    }
}
