package net.theprogrammersworld.herobrine.commandsold;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.AIold.AICore;

public class CmdGraveyard extends SubCommand {

	public CmdGraveyard(HerobrineOld plugin, Logger log) {
		super(plugin, log);
	}

	@Override
	public boolean execute(Player player, String[] args) {
		
		if (args.length > 1) {
			
			Player target = Bukkit.getServer().getPlayer(args[1]);
			
			if (target == null) {
				sendMessage(player, ChatColor.RED + "[Herobrine] " + args[1] + " cannot be sent to Herobrine's Graveyard because they are offline.");
				return true;
			}
			
			if (!target.isOnline()) {
				sendMessage(player, ChatColor.RED + "[Herobrine] " + args[1] + " cannot be sent to Herobrine's Graveyard because they are offline.");
				return true;
			}
			
			if (AICore.isTarget == false) {			
				plugin.getAICore().GraveyardTeleport(Bukkit.getServer().getPlayer(args[1]));
				sendMessage(player, ChatColor.RED + "[Herobrine] " + args[1] + " is now in the Herobrine's Graveyard.");		
			} else {
				sendMessage(player, ChatColor.RED + "[Herobrine] Another player is already in Herobrine's Graveyard. Use "
						+ ChatColor.GREEN + "/herobrine cancel" + ChatColor.RED + " to teleport the current player out of "
						+ "the graveyard.");
			}
			
			return true;
		} 
		
		return false;
	}

	@Override
	public String help() {
		return ChatColor.GREEN + "/herobrine graveyard <player>";
	}

	@Override
	public String helpDesc() {
		return ChatColor.GREEN + "Temporarily teleports the specified player to Herobrine's Graveyard";
	}

}
