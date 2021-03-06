package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Curse extends Core {

    public Curse() {
        super(CoreType.CURSE, AppearType.NORMAL, HerobrineOld.getPluginCore());
    }

    @Override
    public CoreResult CallCore(Object[] data) {

        final Player player = (Player) data[0];

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000, 1));

        for (int i = 0; i < 3; i++) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HerobrineOld.getPluginCore(), new Runnable() {

                @Override
                public void run() {
                    player.getLocation().getWorld().strikeLightning(new Location(player.getLocation().getWorld(),
                            player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ()));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 1));
                }

            }, i * 150L);
        }

        return new CoreResult(true, player.getDisplayName() + " has been cursed by Herobrine.");
    }

}
