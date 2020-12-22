package net.theprogrammersworld.herobrine.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO send log to players with command /herobrine debug [level]
public class Debug {
    private Logger log = LogManager.getLogger("Herobrine");

    public void log(Level level, String msg, Object... args){
        log.log(level, msg, args);
    }

    public void fatal(String msg){
        log(Level.FATAL, msg);
    }

    public void fatal(String msg, Object... args){
        log(Level.FATAL, msg, args);
    }

    public void error(String msg){
        log(Level.ERROR, msg);
    }

    public void error(String msg, Object... args){
        log(Level.ERROR, msg, args);
    }

    public void warn(String msg){
        log(Level.WARN, msg);
    }

    public void warn(String msg, Object... args){
        log(Level.WARN, msg, args);
    }

    public void info(String msg){
        log(Level.INFO, msg);
    }

    public void info(String msg, Object... args){
        log(Level.INFO, msg, args);
    }

    public void debug(String msg){
        log(Level.DEBUG, msg);
    }

    public void debug(String msg, Object... args){
        log(Level.DEBUG, msg, args);
    }

    public void trace(String msg){
        log(Level.TRACE, msg);
    }

    public void trace(String msg, Object... args){
        log(Level.TRACE, msg, args);
    }
}
