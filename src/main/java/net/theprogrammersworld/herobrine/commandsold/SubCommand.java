package net.theprogrammersworld.herobrine.commandsold;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.theprogrammersworld.herobrine.HerobrineOld;

public abstract class SubCommand {
	
	protected HerobrineOld plugin;
	protected Logger logger;
	
	public SubCommand(HerobrineOld plugin, Logger log){
		this.plugin = plugin;
		this.logger = log;
	}
	
	public abstract boolean execute(Player player, String[] args);
	
	public abstract String help();
	
	public abstract String helpDesc();
	
	protected void sendMessage(Player player, String message){
		if(player == null)
			logger.info(ChatColor.stripColor(message));
		else
			player.sendMessage(message);
	}
	

}
