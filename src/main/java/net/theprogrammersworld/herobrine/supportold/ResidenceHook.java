package net.theprogrammersworld.herobrine.supportold;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ResidenceHook {

    public boolean Check() {
        return (Bukkit.getServer().getPluginManager().getPlugin("Residence") != null);

    }

    public boolean isSecuredArea(Location loc) {
        Residence residence = (Residence) Bukkit.getServer().getPluginManager().getPlugin("Residence");
        ClaimedResidence res = residence.getResidenceManager().getByLoc(loc);
        return (res != null);
    }

}
