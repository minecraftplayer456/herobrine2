package net.theprogrammersworld.herobrine.util.debug;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO send log to players with command /herobrine debug [level]
public class Debug {
    private final Logger log = LogManager.getLogger("Herobrine");
    private DebugLevel level;

    public void log(DebugLevel level, String msg, Object... args) {
        if (level.intLevel >= this.level.intLevel) {
            log(level.log4jLevel, msg, args);
        }
    }

    public void log(Level level, String msg, Object... args) {
        log.log(level, msg, args);
    }

    public void fatal(String msg) {
        log(DebugLevel.Fatal, msg);
    }

    public void fatal(String msg, Object... args) {
        log(DebugLevel.Fatal, msg, args);
    }

    public void error(String msg) {
        log(DebugLevel.Error, msg);
    }

    public void error(String msg, Object... args) {
        log(DebugLevel.Error, msg, args);
    }

    public void warn(String msg) {
        log(DebugLevel.Warn, msg);
    }

    public void warn(String msg, Object... args) {
        log(DebugLevel.Warn, msg, args);
    }

    public void info(String msg) {
        log(DebugLevel.Info, msg);
    }

    public void info(String msg, Object... args) {
        log(DebugLevel.Info, msg, args);
    }

    public void debug(String msg) {
        log(DebugLevel.Debug, msg);
    }

    public void debug(String msg, Object... args) {
        log(DebugLevel.Debug, msg, args);
    }

    public void trace(String msg) {
        log(DebugLevel.Trace, msg);
    }

    public void trace(String msg, Object... args) {
        log(DebugLevel.Trace, msg, args);
    }

    public void setLevel(DebugLevel level) {
        this.level = level;
    }
}
