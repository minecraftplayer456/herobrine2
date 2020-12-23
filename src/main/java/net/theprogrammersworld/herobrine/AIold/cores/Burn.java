package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.entity.Player;

public class Burn extends Core {

    public Burn() {
        super(CoreType.BURN, AppearType.NORMAL, HerobrineOld.getPluginCore());
    }

    @Override
    public CoreResult CallCore(Object[] data) {
        Player player = (Player) data[0];
        player.setFireTicks(800);
        return new CoreResult(true, player.getDisplayName() + " was burned by Herobrine.");
    }

}
