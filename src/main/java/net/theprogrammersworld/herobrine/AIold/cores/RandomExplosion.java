package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RandomExplosion extends Core {

    public RandomExplosion() {
        super(CoreType.RANDOM_EXPLOSION, AppearType.NORMAL, HerobrineOld.getPluginCore());
    }

    @Override
    public CoreResult CallCore(Object[] data) {

        Player player = (Player) data[0];
        if (PluginCore.getConfigDB().Explosions) {
            if (PluginCore.getSupport().checkBuild(player.getLocation())) {

                Location loc = player.getLocation();
                int x = loc.getBlockX() + (Utils.getRandomGen().nextInt(16) - 8);
                int y = loc.getBlockY();
                int z = loc.getBlockZ() + (Utils.getRandomGen().nextInt(16) - 8);
                loc.getWorld().createExplosion(new Location(loc.getWorld(), x, y, z), 1.0f);

            } else {
                return new CoreResult(true, "Herobrine cannot produce an explosion near " + player.getDisplayName() +
                        " because they are in a secure area.");
            }
        } else {
            return new CoreResult(true, "Herobrine explosions are not allowed.");
        }
        return new CoreResult(true, "Herobrine produced an explosion near " + player.getDisplayName() + ".");
    }

}
