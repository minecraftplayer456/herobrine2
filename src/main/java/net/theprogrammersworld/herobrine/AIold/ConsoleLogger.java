package net.theprogrammersworld.herobrine.AIold;

import net.theprogrammersworld.herobrine.HerobrineOld;

import java.util.logging.Logger;

public class ConsoleLogger {

    static Logger log = Logger.getLogger("Minecraft");

    public void info(String text) {
        if (HerobrineOld.isDebugging) {
            log.info(text);
        }
    }

}
