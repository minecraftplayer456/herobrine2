package net.theprogrammersworld.herobrine.AIold.cores;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.AIold.AICore;
import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.AIold.Message;

public class Totem extends Core {

	public Totem() {
		super(CoreType.TOTEM, AppearType.APPEAR, HerobrineOld.getPluginCore());
	}

	public CoreResult CallCore(Object[] data) {
		return TotemCall((Location) data[0], (String) data[1]);
	}

	public CoreResult TotemCall(Location loc, String caller) {

		AICore.isTotemCalled = false;
		loc.getWorld().strikeLightning(loc);
		
		if (PluginCore.getConfigDB().TotemExplodes == true) {
			loc.getWorld().createExplosion(loc, 5);
		}
		
		if (Bukkit.getServer().getPlayer(caller) != null) {
			
			if (Bukkit.getServer().getPlayer(caller).isOnline()) {
				PluginCore.getAICore().setCoreTypeNow(CoreType.TOTEM);
				PluginCore.getAICore().setAttackTarget(Bukkit.getServer().getPlayer(caller));
				Player player = (Player) Bukkit.getServer().getPlayer(caller);

				Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
				
				if (Bukkit.getServer().getOnlinePlayers().size() > 0) {
					for(Player onlinePlayer : onlinePlayers) {
						Location ploc = (Location) onlinePlayer.getLocation();
						if (onlinePlayer.getName() != player.getName() && ploc.getX() + 10 > loc.getX()
								&& ploc.getX() - 10 < loc.getX() && ploc.getZ() + 10 > loc.getZ()
								&& ploc.getZ() - 10 < loc.getZ()) {

							Message.sendRandomMessage(onlinePlayer);
							if (PluginCore.getConfigDB().UsePotionEffects) {
								onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000, 1));
								onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000, 1));
								onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 1));
							}
						}
					}

				}
			} else {
				Player target = null;

				Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
				
				if (Bukkit.getServer().getOnlinePlayers().size() > 0) {
					for(Player onlinePlayer : onlinePlayers) {
						
						Location ploc = (Location) onlinePlayer.getLocation();
						
						if (ploc.getX() + 10 > loc.getX() && ploc.getX() - 10 < loc.getX()
							&& ploc.getZ() + 10 > loc.getZ() && ploc.getZ() - 10 < loc.getZ()) {
						
							target = onlinePlayer;
							break;

						}
						
					}

				}
				
				if (target != null) {

					PluginCore.getAICore().CancelTarget(CoreType.TOTEM);
					PluginCore.getAICore().setAttackTarget(target);
					Player player = (Player) target;
					if (Bukkit.getServer().getOnlinePlayers().size() > 0) {
						for(Player onlinePlayer : onlinePlayers) {
							
							Location ploc = (Location) onlinePlayer.getLocation();
							if (onlinePlayer.getName() != player.getName() 
								&& ploc.getX() + 20 > loc.getX()
								&& ploc.getX() - 20 < loc.getX() 
								&& ploc.getZ() + 20 > loc.getZ()
								&& ploc.getZ() - 20 < loc.getZ()
								) {

								Message.sendRandomMessage(onlinePlayer);
								onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000, 1));
								onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000, 1));
								onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 1));

							}
						}

					}

				}

			}

		}
		
		return new CoreResult(false, "Herobrine's totem called!");
	}
}
