package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.AICore;
import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Graveyard extends Core {

    private List<LivingEntity> LivingEntities;
    private int ticks = 0;
    private double savedX = 0;
    private double savedY = 0;
    private double savedZ = 0;
    private World savedWorld = null;
    private Player savedPlayer = null;

    public Graveyard() {
        super(CoreType.GRAVEYARD, AppearType.APPEAR, HerobrineOld.getPluginCore());
    }

    public CoreResult CallCore(Object[] data) {
        return Teleport((Player) data[0]);
    }

    public CoreResult Teleport(Player player) {
        if (HerobrineOld.getPluginCore().getConfigDB().UseGraveyardWorld == true) {
            if (!HerobrineOld.getPluginCore().getAICore().checkAncientSword(player.getInventory())) {
                LivingEntities = Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName).getLivingEntities();
                for (int i = 0; i <= LivingEntities.size() - 1; i++) {

                    if (LivingEntities.get(i) instanceof Player || LivingEntities.get(i).getEntityId() == PluginCore.HerobrineEntityID) {
                    } else {

                        LivingEntities.get(i).remove();

                    }

                }

                Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName).setTime(15000);
                AICore.PlayerTarget = player;
                Location loc = player.getLocation();
                savedX = loc.getX();
                savedY = loc.getY();
                savedZ = loc.getZ();
                savedWorld = loc.getWorld();
                savedPlayer = player;
                cachePreGraveyardPositionToDisk(loc, player);
                loc.setWorld(Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName));
                loc.setX(-2.49);
                loc.setY(4);
                loc.setZ(10.69);
                loc.setYaw(-179.85f);
                loc.setPitch(0.44999f);
                player.teleport(loc);

                Start();

                AICore.isTarget = true;
                Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName).setStorm(false);

                return new CoreResult(true, player.getDisplayName() + " was successfully teleported to Herobrine's Graveyard.");
            } else {
                return new CoreResult(false, player.getDisplayName() + " cannot be attacked because they have an Ancient Sword.");
            }
        }
        return new CoreResult(false, "Herobrine's Graveyard is disabled.");
    }

    public void Start() {

        ticks = 0;
        PluginCore.HerobrineNPC.moveTo(new Location(Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName), -2.49, 4, -4.12));
        HandlerInterval();

    }

    public void HandlerInterval() {

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AICore.plugin, new Runnable() {
            public void run() {
                Handler();
            }
        }, 1 * 5L);
    }

    public void Handler() {

        LivingEntities = Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName).getLivingEntities();
        for (int i = 0; i <= LivingEntities.size() - 1; i++) {

            if (LivingEntities.get(i) instanceof Player
                    || LivingEntities.get(i).getEntityId() == PluginCore.HerobrineEntityID) {
            } else {

                LivingEntities.get(i).remove();

            }

        }

        if (savedPlayer.isDead() == true
                || savedPlayer.isOnline() == false
                || savedPlayer.getLocation().getWorld() != Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName)
                || this.ticks == 90 || AICore.isTarget == false) {

            if (AICore.PlayerTarget == savedPlayer) {
                HerobrineOld.getPluginCore().getAICore().CancelTarget(CoreType.GRAVEYARD);
            }

            savedPlayer.teleport(new Location(savedWorld, savedX, savedY, savedZ));
            deletePreGraveyardCache(savedPlayer);

        } else {
            Location ploc = savedPlayer.getLocation();
            ploc.setY(ploc.getY() + 1.5);
            PluginCore.HerobrineNPC.lookAtPoint(ploc);
            if (ticks == 1) {
                PluginCore.HerobrineNPC.moveTo(
                        new Location(Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName), -2.49, 4, -4.12));
            } else if (ticks == 40) {
                PluginCore.HerobrineNPC.moveTo(
                        new Location(Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName), -2.49, 4, -0.5));
            } else if (ticks == 60) {
                PluginCore.HerobrineNPC.moveTo(
                        new Location(Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName), -2.49, 4, 5.1));

            } else if (ticks == 84) {
                PluginCore.HerobrineNPC.moveTo(
                        new Location(Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName), -2.49, 4, 7.5));

            }

            Random randomGen = Utils.getRandomGen();

            if (randomGen.nextInt(4) == 1) {
                Location newloc = new Location(Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName),
                        randomGen.nextInt(400), (double) Utils.getRandomGen().nextInt(20) + 20,
                        randomGen.nextInt(400));
                Bukkit.getServer().getWorld(HerobrineOld.getPluginCore().getConfigDB().HerobrineWorldName).strikeLightning(newloc);
            }
            ticks++;
            HandlerInterval();

        }

    }

    public Location getSavedLocation() {
        return new Location(savedWorld, savedX, savedY, savedZ);
    }

    private void cachePreGraveyardPositionToDisk(Location loc, Player player) {
        // Saves a cache of the given player's position prior to getting transported to the graveyard to the disk.
        try {
            FileWriter cache = new FileWriter("plugins/Herobrine/pregraveyard_caches/" + player.getUniqueId());
            cache.write(Double.toString(loc.getX()) + '\n');
            cache.write(Double.toString(loc.getY()) + '\n');
            cache.write(Double.toString(loc.getZ()) + '\n');
            cache.write(loc.getWorld().getName());
            cache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletePreGraveyardCache(Player player) {
        // Deletes the cache of the given player's position prior to getting teleported to the graveyard.
        new File("plugins/Herobrine/pregraveyard_caches/" + player.getUniqueId()).delete();
    }

}
