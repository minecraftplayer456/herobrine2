package net.theprogrammersworld.herobrine.supportold;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class WorldGuardHook {
    public boolean Check() {

        return (Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") != null);
    }

    public boolean isSecuredArea(Location loc) {
        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        com.sk89q.worldedit.util.Location we_loc = BukkitAdapter.adapt(loc);
        ApplicableRegionSet set = query.getApplicableRegions(new com.sk89q.worldedit.util.Location(we_loc.getExtent(), we_loc.getX(), we_loc.getY(), we_loc.getZ()));
        if (set != null) {
            return set.size() != 0;
        } else {
            return false;
        }
    }
}
