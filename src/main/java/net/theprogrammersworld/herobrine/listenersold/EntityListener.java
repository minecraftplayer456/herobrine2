package net.theprogrammersworld.herobrine.listenersold;

import net.theprogrammersworld.herobrine.AIold.AICore;
import net.theprogrammersworld.herobrine.AIold.Core.CoreType;
import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.Utils;
import net.theprogrammersworld.herobrine.entityold.MobType;
import net.theprogrammersworld.herobrine.miscold.ItemName;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class EntityListener implements Listener {

    private final ArrayList<String> equalsLore = new ArrayList<String>();
    private final ArrayList<String> equalsLoreS = new ArrayList<String>();
    private ItemStack itemInHand = null;
    private ArrayList<String> getLore = new ArrayList<String>();
    private HerobrineOld PluginCore = null;

    public EntityListener(HerobrineOld plugin) {
        equalsLore.add("Herobrine artifact");
        equalsLore.add("Bow of Teleporting");
        equalsLoreS.add("Herobrine artifact");
        equalsLoreS.add("Sword of Lightning");
        PluginCore = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (!HerobrineOld.isNPCDisabled) {
            if (PluginCore.getConfigDB().useWorlds.contains(event.getEntity().getLocation().getWorld().getName())) {

                Entity entity = event.getEntity();
                EntityType creatureType = event.getEntityType();

                if (event.isCancelled())
                    return;

                if (creatureType == EntityType.ZOMBIE) {
                    if (PluginCore.getConfigDB().UseNPC_Warrior) {
                        if (Utils.getRandomGen().nextInt(100) < PluginCore.getConfigDB().npc.getInt("npc.Warrior.SpawnChance")) {

                            if (PluginCore.getEntityManager().isCustomMob(entity.getEntityId()) == false) {
                                LivingEntity ent = (LivingEntity) entity;

                                ent.setHealth(0);
                                PluginCore.getEntityManager().spawnCustomZombie(event.getLocation(), MobType.HEROBRINE_WARRIOR);

                                return;
                            }
                        }
                    }
                } else if (creatureType == EntityType.SKELETON) {
                    if (PluginCore.getConfigDB().UseNPC_Demon) {
                        if (Utils.getRandomGen().nextInt(100) < PluginCore.getConfigDB().npc.getInt("npc.Demon.SpawnChance")) {

                            if (PluginCore.getEntityManager().isCustomMob(entity.getEntityId()) == false) {
                                LivingEntity ent = (LivingEntity) entity;

                                ent.setHealth(0);
                                PluginCore.getEntityManager().spawnCustomSkeleton(event.getLocation(), MobType.DEMON);

                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        if (PluginCore.getEntityManager().isCustomMob(event.getEntity().getEntityId())) {
            PluginCore.getEntityManager().removeMob(event.getEntity().getEntityId());
        }
    }

    @EventHandler
    public void EntityTargetEvent(EntityTargetLivingEntityEvent e) {
        LivingEntity lv = e.getTarget();

        if (lv == null)
            return;

        if (lv.getEntityId() == PluginCore.HerobrineEntityID) {
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {

            Arrow arrow = (Arrow) event.getEntity();
            if (arrow.getShooter() instanceof Player) {

                Player player = (Player) arrow.getShooter();
                if (player.getInventory().getItemInMainHand() != null) {

                    itemInHand = player.getInventory().getItemInMainHand();
                    if (itemInHand.getType() != null) {

                        if (itemInHand.getType() == Material.BOW) {
                            getLore = ItemName.getLore(itemInHand);
                            if (getLore != null) {

                                if (getLore.containsAll(equalsLore)) {

                                    if (PluginCore.getConfigDB().UseArtifactBow) {

                                        player.teleport(arrow.getLocation());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {

        if (event.getEntity().getEntityId() == PluginCore.HerobrineEntityID) {
            event.setCancelled(true);
            event.setDamage(0);
            return;
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity().getEntityId() == PluginCore.HerobrineEntityID) {
            if (event instanceof EntityDamageByEntityEvent) {

                EntityDamageByEntityEvent dEvent = (EntityDamageByEntityEvent) event;
                if (PluginCore.getConfigDB().Killable == true
                        && PluginCore.getAICore().getCoreTypeNow() != CoreType.GRAVEYARD) {

                    if (dEvent.getDamager() instanceof Player) {
                        if (event.getDamage() >= HerobrineOld.HerobrineHP) {

                            HerobrineDropItems();

                            PluginCore.getAICore().CancelTarget(CoreType.ANY);
                            HerobrineOld.HerobrineHP = HerobrineOld.HerobrineMaxHP;
                            Player player = (Player) dEvent.getDamager();
                            player.sendMessage("<Herobrine> " + PluginCore.getConfigDB().DeathMessage);

                        } else {
                            HerobrineOld.HerobrineHP -= event.getDamage();
                            PluginCore.HerobrineNPC.HurtAnimation();
                            AICore.log.info("HIT: " + event.getDamage());
                        }
                    } else if (dEvent.getDamager() instanceof Projectile) {

                        Arrow arrow = (Arrow) dEvent.getDamager();
                        if (arrow.getShooter() instanceof Player) {
                            if (PluginCore.getAICore().getCoreTypeNow() == CoreType.RANDOM_POSITION) {
                                PluginCore.getAICore().CancelTarget(CoreType.ANY);
                                PluginCore.getAICore().setAttackTarget((Player) arrow.getShooter());
                            } else {

                                if (event.getDamage() >= HerobrineOld.HerobrineHP) {

                                    HerobrineDropItems();

                                    PluginCore.getAICore().CancelTarget(CoreType.ANY);
                                    HerobrineOld.HerobrineHP = HerobrineOld.HerobrineMaxHP;
                                    Player player = (Player) arrow.getShooter();
                                    player.sendMessage("<Herobrine> " + PluginCore.getConfigDB().DeathMessage);

                                } else {
                                    HerobrineOld.HerobrineHP -= event.getDamage();
                                    PluginCore.HerobrineNPC.HurtAnimation();
                                    AICore.log.info("HIT: " + event.getDamage());
                                }

                            }
                        } else {
                            if (PluginCore.getAICore().getCoreTypeNow() == CoreType.RANDOM_POSITION) {
                                Location newloc = PluginCore.HerobrineNPC.getBukkitEntity().getLocation();
                                newloc.setY(-20);
                                PluginCore.HerobrineNPC.moveTo(newloc);
                                PluginCore.getAICore().CancelTarget(CoreType.ANY);
                            }
                        }
                    } else {
                        if (PluginCore.getAICore().getCoreTypeNow() == CoreType.RANDOM_POSITION) {
                            Location newloc = PluginCore.HerobrineNPC.getBukkitEntity().getLocation();
                            newloc.setY(-20);
                            PluginCore.HerobrineNPC.moveTo(newloc);
                            PluginCore.getAICore().CancelTarget(CoreType.ANY);
                        }
                    }
                }

            }

            event.setCancelled(true);
            event.setDamage(0);
            return;

        } else {
            if (event instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent dEvent = (EntityDamageByEntityEvent) event;
                if (dEvent.getDamager() instanceof Player) {
                    Player player = (Player) dEvent.getDamager();
                    if (player.getInventory().getItemInMainHand() != null) {
                        if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
                            if (ItemName.getLore(player.getInventory().getItemInMainHand()) != null) {
                                itemInHand = player.getInventory().getItemInMainHand();
                                getLore = ItemName.getLore(itemInHand);
                                if (getLore.containsAll(equalsLoreS)) {
                                    if (PluginCore.getConfigDB().UseArtifactSword) {
                                        if (Utils.getRandomGen().nextBoolean()) {
                                            player.getLocation().getWorld().strikeLightning(event.getEntity().getLocation());
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (dEvent.getDamager() instanceof Zombie) {
                    Zombie zmb = (Zombie) dEvent.getDamager();
                    if (zmb.getCustomName() == "Artifact Guardian" || zmb.getCustomName() == "Herobrine Warrior") {

                        dEvent.setDamage(dEvent.getDamage() * 3);
                    }

                } else if (dEvent.getDamager() instanceof Skeleton) {
                    Skeleton zmb = (Skeleton) dEvent.getDamager();
                    if (zmb.getCustomName() == "Demon") {

                        dEvent.setDamage(dEvent.getDamage() * 3);
                    }

                }
            }
            if (event.getCause() != null) {
                if (event.getCause() == DamageCause.LIGHTNING) {
                    if (event.getEntity() instanceof Player) {
                        if (event.getEntity().getEntityId() != PluginCore.HerobrineEntityID) {
                            Player player = (Player) event.getEntity();
                            if (player.getInventory().getItemInMainHand() != null) {
                                if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
                                    if (ItemName.getLore(player.getInventory().getItemInMainHand()) != null) {
                                        itemInHand = player.getInventory().getItemInMainHand();
                                        getLore = ItemName.getLore(itemInHand);
                                        if (getLore.containsAll(equalsLoreS)) {
                                            if (PluginCore.getConfigDB().UseArtifactSword) {
                                                event.setDamage(0);
                                                event.setCancelled(true);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }
    }

    protected void HerobrineDropItems() {
        Object[] items = PluginCore.getConfigDB().config.getConfigurationSection("config.Drops").getKeys(false).toArray();
        for (Object itemObj : items) {
            final String item = itemObj.toString();
            final int chance = new Random().nextInt(100);
            if (chance <= PluginCore.getConfigDB().config.getInt("config.Drops." + item + ".chance")) {
                PluginCore.HerobrineNPC.getBukkitEntity().getLocation().getWorld().dropItemNaturally(PluginCore.HerobrineNPC.getBukkitEntity()
                        .getLocation(), new ItemStack(Material.matchMaterial(item), PluginCore.getConfigDB().config
                        .getInt("config.Drops." + item + ".count")));
            }
        }
    }

}
