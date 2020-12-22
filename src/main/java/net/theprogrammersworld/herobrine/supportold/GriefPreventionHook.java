package net.theprogrammersworld.herobrine.supportold;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class GriefPreventionHook {

	public boolean Check() {
		return (Bukkit.getServer().getPluginManager().getPlugin("GriefPrevention") != null);
	}

	public boolean isSecuredArea(Location loc) {
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(loc, false, null);
		return (claim != null);
	}
}
