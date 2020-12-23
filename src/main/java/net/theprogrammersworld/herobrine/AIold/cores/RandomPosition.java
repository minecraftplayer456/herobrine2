package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.AICore;
import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.ConfigDBOld;
import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.NPCold.AI.Path;
import net.theprogrammersworld.herobrine.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collection;

public class RandomPosition extends Core {

    private int randomTicks = 0;
    private int randomMoveTicks = 0;
    private boolean RandomMoveIsPlayer = false;

    public RandomPosition() {
        super(CoreType.RANDOM_POSITION, AppearType.APPEAR, HerobrineOld.getPluginCore());
    }

    public int getRandomTicks() {
        return this.randomTicks;
    }

    public void setRandomTicks(int i) {
        this.randomTicks = i;
    }

    public int getRandomMoveTicks() {
        return this.randomMoveTicks;
    }

    public void setRandomMoveTicks(int i) {
        this.randomMoveTicks = i;
    }

    public CoreResult CallCore(Object[] data) {
        return setRandomPosition((World) data[0]);
    }

    public CoreResult setRandomPosition(World world) {
        if (PluginCore.getConfigDB().UseWalkingMode) {
            if (randomTicks != 3) {
                randomTicks++;
                if (PluginCore.getAICore().getCoreTypeNow() != CoreType.RANDOM_POSITION && AICore.isTarget == false) {
                    Location newloc = getRandomLocation(world);
                    if (newloc != null) {

                        PluginCore.HerobrineNPC.moveTo(newloc);
                        newloc.setX(newloc.getX() + 2);
                        newloc.setY(newloc.getY() + 1.5);
                        PluginCore.HerobrineNPC.lookAtPoint(newloc);
                        randomTicks = 0;
                        AICore.log.info("[Herobrine] Herobrine is now in RandomLocation mode.");
                        PluginCore.getAICore().Start_RM();
                        PluginCore.getAICore().Start_RS();
                        PluginCore.getAICore().Start_CG();
                        RandomMoveIsPlayer = false;
                        return new CoreResult(true, "Herobrine is now in WalkingMode.");
                    } else {
                        AICore.log.info("[Herobrine] RandomPosition Failed.");
                        return setRandomPosition(world);
                    }
                }
            } else {
                return new CoreResult(false, "WalkingMode - Find location failed.");
            }
        } else {
            return new CoreResult(false, "WalkingMode is disabled.");
        }
        return new CoreResult(false, "WalkingMode failed.");
    }

    public Location getRandomLocation(World world) {
        int r_nxtX = PluginCore.getConfigDB().WalkingModeXRadius;
        int nxtX = r_nxtX;
        if (nxtX < 0) {
            nxtX = -nxtX;
        }
        int r_nxtZ = PluginCore.getConfigDB().WalkingModeZRadius;
        int nxtZ = r_nxtZ;
        if (nxtZ < 0) {
            nxtZ = -nxtZ;
        }
        int randx = Utils.getRandomGen().nextInt(nxtX);

        int randy = 0;

        int randz = Utils.getRandomGen().nextInt(nxtZ);

        int randxp = Utils.getRandomGen().nextInt(1);

        int randzp = Utils.getRandomGen().nextInt(1);

        if (randxp == 0 && randx != 0) {
            randx = -(randx);
        }
        if (randzp == 0 && randz != 0) {
            randz = -(randz);
        }

        randx = randx + PluginCore.getConfigDB().WalkingModeFromXRadius;
        randz = randz + PluginCore.getConfigDB().WalkingModeFromZRadius;

        if (world != null) {
            randy = world.getHighestBlockYAt(randx, randz);
        } else {
            return null;
        }

        if (world.getBlockAt(randx, randy + 1, randz).getType() == Material.AIR
                && world.getBlockAt(randx, randy + 2, randz).getType() == Material.AIR) {
            if (world.getBlockAt(randx, randy, randz).getType() != Material.AIR
                    && world.getBlockAt(randx, randy, randz).getType() != Material.WATER
                    && world.getBlockAt(randx, randy, randz).getType() != Material.LAVA
                    && world.getBlockAt(randx, randy, randz).getType() != Material.GRASS
                    && world.getBlockAt(randx, randy, randz).getType() != Material.SNOW
                    && world.getBlockAt(randx, randy, randz).getType() != Material.ACACIA_LEAVES
                    && world.getBlockAt(randx, randy, randz).getType() != Material.BIRCH_LEAVES
                    && world.getBlockAt(randx, randy, randz).getType() != Material.DARK_OAK_LEAVES
                    && world.getBlockAt(randx, randy, randz).getType() != Material.JUNGLE_LEAVES
                    && world.getBlockAt(randx, randy, randz).getType() != Material.OAK_LEAVES
                    && world.getBlockAt(randx, randy, randz).getType() != Material.SPRUCE_LEAVES
                    && world.getBlockAt(randx, randy, randz).getType() != Material.WHEAT
                    && world.getBlockAt(randx, randy, randz).getType() != Material.TORCH
                    && world.getBlockAt(randx, randy, randz).getType() != Material.REDSTONE_TORCH
                    && world.getBlockAt(randx, randy, randz).getType() != Material.REDSTONE) {

                randy++;

                AICore.log.info("[Herobrine] RandomLocation "
                        + world.getBlockAt(randx, randy, randz).getType().toString() + " is X:" + randx + " Y:"
                        + randy + " Z:" + randz);
                return new Location(world, (float) randx, (float) randy, (float) randz);

            }
        }
        return null;
    }

    public void RandomMove() {
        if (PluginCore.getAICore().getCoreTypeNow() == CoreType.RANDOM_POSITION && AICore.isTarget == false
                && RandomMoveIsPlayer == false) {
            HerobrineOld.HerobrineHP = HerobrineOld.HerobrineMaxHP;

            if (Utils.getRandomGen().nextInt(5) == 3) {
                Location loc = PluginCore.HerobrineNPC.getBukkitEntity().getLocation();
                Path path = new Path((float) loc.getX() + Utils.getRandomGen().nextInt(30) - 15,
                        (float) loc.getZ() + Utils.getRandomGen().nextInt(30) - 15,
                        PluginCore);
                PluginCore.getPathManager().setPath(path);
            }

        }

    }

    public void CheckGravity() {

        if (PluginCore.getAICore().getCoreTypeNow() == CoreType.RANDOM_POSITION && AICore.isTarget == false) {

            Location hbloc = PluginCore.HerobrineNPC.getBukkitEntity().getLocation();
            World w = hbloc.getWorld();
            ConfigDBOld config = PluginCore.getConfigDB();

            if (hbloc.getBlockX() < config.WalkingModeXRadius + config.WalkingModeFromXRadius
                    && hbloc.getBlockX() > (-config.WalkingModeXRadius) + config.WalkingModeFromXRadius
                    && hbloc.getBlockZ() < config.WalkingModeZRadius + config.WalkingModeFromZRadius
                    && hbloc.getBlockZ() > (-config.WalkingModeZRadius) + config.WalkingModeFromZRadius) {

                if (!w.getBlockAt(hbloc.getBlockX(), hbloc.getBlockY() - 1, hbloc.getBlockZ()).getType().isSolid()) {

                    hbloc.setY(hbloc.getY() - 1);

                    PluginCore.HerobrineNPC.moveTo(hbloc);

                }
            } else {
                PluginCore.getAICore().CancelTarget(CoreType.RANDOM_POSITION);
            }
        }
    }

    public void CheckPlayerPosition() {
        boolean isThere = false;
        Location loc = PluginCore.HerobrineNPC.getBukkitEntity().getLocation();
        Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

        if (Bukkit.getServer().getOnlinePlayers().size() > 0) {

            for (Player player : onlinePlayers) {

                if (PluginCore.HerobrineEntityID != player.getEntityId()) {
                    Location ploc = player.getLocation();

                    if (ploc.getWorld() == loc.getWorld()
                            && ploc.getX() + 7 > loc.getX()
                            && ploc.getX() - 7 < loc.getX()
                            && ploc.getZ() + 7 > loc.getZ()
                            && ploc.getZ() - 7 < loc.getZ()
                            && ploc.getY() + 7 > loc.getY()
                            && ploc.getY() - 7 < loc.getY()) {

                        loc.setY(-20);
                        PluginCore.HerobrineNPC.moveTo(loc);
                        PluginCore.getAICore().CancelTarget(CoreType.RANDOM_POSITION);
                        RandomMoveIsPlayer = false;
                        PluginCore.getAICore().setAttackTarget(player);
                        break;
                    } else {

                        if (ploc.getWorld() == loc.getWorld()
                                && ploc.getX() + 15 > loc.getX()
                                && ploc.getX() - 15 < loc.getX()
                                && ploc.getZ() + 15 > loc.getZ()
                                && ploc.getZ() - 15 < loc.getZ()
                                && ploc.getY() + 15 > loc.getY()
                                && ploc.getY() - 15 < loc.getY()) {

                            ploc.setY(ploc.getY() + 1.5);
                            PluginCore.HerobrineNPC.lookAtPoint(ploc);
                            PluginCore.getPathManager().setPath(null);
                            isThere = true;
                            break;
                        }
                    }
                }
            }
        }

        RandomMoveIsPlayer = isThere;

    }

}
