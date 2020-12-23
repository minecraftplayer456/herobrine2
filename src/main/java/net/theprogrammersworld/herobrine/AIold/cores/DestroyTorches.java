package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class DestroyTorches extends Core {

    public DestroyTorches() {
        super(CoreType.DESTROY_TORCHES, AppearType.NORMAL, HerobrineOld.getPluginCore());
    }

    public CoreResult CallCore(Object[] data) {
        return destroyTorches((Location) data[0]);
    }

    public CoreResult destroyTorches(Location loc) {
        if (HerobrineOld.getPluginCore().getConfigDB().DestroyTorches == true) {

            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();
            World world = loc.getWorld();

            int i = -(HerobrineOld.getPluginCore().getConfigDB().DestroyTorchesRadius); // Y
            int ii = -(HerobrineOld.getPluginCore().getConfigDB().DestroyTorchesRadius); // X
            int iii = -(HerobrineOld.getPluginCore().getConfigDB().DestroyTorchesRadius); // Z

            for (i = -(HerobrineOld.getPluginCore().getConfigDB().DestroyTorchesRadius); i <= HerobrineOld.getPluginCore()
                    .getConfigDB().DestroyTorchesRadius; i++) {
                for (ii = -(HerobrineOld.getPluginCore().getConfigDB().DestroyTorchesRadius); ii <= HerobrineOld
                        .getPluginCore().getConfigDB().DestroyTorchesRadius; ii++) {
                    for (iii = -(HerobrineOld.getPluginCore().getConfigDB().DestroyTorchesRadius); iii <= HerobrineOld
                            .getPluginCore().getConfigDB().DestroyTorchesRadius; iii++) {
                        if (world.getBlockAt(x + ii, y + i, z + iii).getType() == Material.TORCH) {
                            world.getBlockAt(x + ii, y + i, z + iii).breakNaturally();
                            return new CoreResult(true, "Torches successfully destroyed by Herobrine.");
                        }
                    }
                }
            }

        }
        return new CoreResult(false, "Herobrine could not destroy the torches.");
    }

}
