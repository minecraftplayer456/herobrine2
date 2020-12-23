package net.theprogrammersworld.herobrine.supportold;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class FactionsHook {

    public boolean Check() {
        return (Bukkit.getServer().getPluginManager().getPlugin("Factions") != null);
    }

    public boolean isSecuredArea(Location loc) {
        return !BoardColl.get().getFactionAt(PS.valueOf(loc)).getComparisonName().equalsIgnoreCase("Wilderness");
    }
}
