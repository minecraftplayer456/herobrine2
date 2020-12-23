package net.theprogrammersworld.herobrine.supportold;

import com.palmergames.bukkit.towny.TownyAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class TownyHook {
    public boolean Check() {
        return (Bukkit.getServer().getPluginManager().getPlugin("Towny") != null);

    }

    public boolean isSecuredArea(Location loc) {
        return (TownyAPI.getInstance().getTownBlock(loc) != null);
    }
}
