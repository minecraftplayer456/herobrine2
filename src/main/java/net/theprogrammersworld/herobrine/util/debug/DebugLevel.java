package net.theprogrammersworld.herobrine.util.debug;

import org.apache.logging.log4j.Level;

public enum DebugLevel {
    None(0, "none", Level.OFF),
    Trace(1, "trace", Level.INFO),
    Debug(2, "debug", Level.INFO),
    Info(3, "info", Level.INFO),
    Warn(4, "warn", Level.WARN),
    Error(5, "error", Level.ERROR),
    Fatal(6, "fatal", Level.FATAL),
    All(7, "all", Level.ALL);

    public final int intLevel;
    public final String name;
    public final Level log4jLevel;

    DebugLevel(int intLevel, String name, Level log4jLevel) {
        this.intLevel = intLevel;
        this.name = name;
        this.log4jLevel = log4jLevel;
    }

    public static DebugLevel valueOfName(String name) {
        for (DebugLevel value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }

        throw new EnumConstantNotPresentException(DebugLevel.class, name);
    }
}