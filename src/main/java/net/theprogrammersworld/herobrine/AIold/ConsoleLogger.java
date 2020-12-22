package net.theprogrammersworld.herobrine.AIold;

import java.util.logging.Logger;

import net.theprogrammersworld.herobrine.HerobrineOld;

public class ConsoleLogger {

	static Logger log = Logger.getLogger("Minecraft");
	
	public void info(String text){
		if (HerobrineOld.isDebugging){
			log.info(text);
		}
	}
	
}
