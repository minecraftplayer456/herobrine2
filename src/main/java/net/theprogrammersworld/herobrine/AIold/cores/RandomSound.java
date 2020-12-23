package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.Bukkit;

public class RandomSound extends Core {

    public RandomSound() {
        super(CoreType.RANDOM_SOUND, AppearType.NORMAL, HerobrineOld.getPluginCore());
    }

    @Override
    public CoreResult CallCore(final Object[] data) {

        int multip = 1;

        while (multip != 7) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HerobrineOld.getPluginCore(), new Runnable() {

                @Override
                public void run() {

                    HerobrineOld.getPluginCore().getAICore().getCore(CoreType.SOUNDF).RunCore(data);

                }

            }, multip * 30L);
            multip++;
        }

        return new CoreResult(true, "Herobrine haunted the target with sound.");
    }

}
