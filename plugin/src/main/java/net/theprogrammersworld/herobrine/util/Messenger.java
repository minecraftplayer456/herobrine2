package net.theprogrammersworld.herobrine.util;

import net.theprogrammersworld.herobrine.api.util.IMessenger;
import net.theprogrammersworld.herobrine.api.util.MessageLevel;
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
        if (level.getLevel() >= this.level.getLevel()) {
            logger.log(getLog4JLevel(level), msg, args);
        }
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
}
