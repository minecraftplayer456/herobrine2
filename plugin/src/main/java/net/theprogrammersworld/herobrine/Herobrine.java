package net.theprogrammersworld.herobrine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.theprogrammersworld.herobrine.api.HerobrineApi;
import net.theprogrammersworld.herobrine.api.IHerobrinePlugin;
import net.theprogrammersworld.herobrine.api.entity.IPlayerEntity;
import net.theprogrammersworld.herobrine.api.nms.INMSServer;
import net.theprogrammersworld.herobrine.api.util.message.IMessenger;
import net.theprogrammersworld.herobrine.api.util.settings.IHerobrineSettings;
import net.theprogrammersworld.herobrine.entity.EntityCore;
import net.theprogrammersworld.herobrine.network.NetworkCore;
import net.theprogrammersworld.herobrine.nms.bridge.Bridge;
import net.theprogrammersworld.herobrine.npc.profile.Profile;
import net.theprogrammersworld.herobrine.util.message.Messenger;
import net.theprogrammersworld.herobrine.util.settings.HerobrineSettings;

public class Herobrine extends JavaPlugin implements IHerobrinePlugin {

    private static IHerobrinePlugin plugin;

    private Bridge bridge;
    private EntityCore entityCore;
    private NetworkCore networkCore;

    private Messenger messenger;

    private HerobrineSettings settings;

    public static IHerobrinePlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        //Initialize api
        HerobrineApi.setImplementation(this);

        //Initialize messenger
        messenger = new Messenger("Herobrine");

        //Initialize configuration
        settings = new HerobrineSettings(getDataFolder());
        settings.load();

        //Initialize nms bridge
        bridge = new Bridge();

        //Initializing network management
        networkCore = new NetworkCore();

        //Initialize entity management
        entityCore = new EntityCore((INMSServer) bridge.getServer(), networkCore);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            Player player = (Player) sender;

            IPlayerEntity entity = entityCore.createPlayerEntity(bridge.getWorld(player.getWorld()), new Profile("Test"));
            entityCore.spawnPlayerEntity(entity, player.getLocation());

            Bukkit.getScheduler().runTaskTimer(this, () -> {
                Location newLocation = player.getLocation().clone();
                entity.teleport(newLocation.add(1, 0, 0));
            }, 20L * 3, 20L);
        }
        return true;
    }

    @Override
    public void onDisable() {
        plugin = null;

        //Save configuration
        settings.save();
    }

    @Override
    public IMessenger getMessenger() {
        return messenger;
    }

    @Override
    public IHerobrineSettings getSettings() {
        return settings;
    }

    @Override
    public Bridge getBridge() {
        return bridge;
    }

    @Override
    public NetworkCore getNetworkCore() {
        return networkCore;
    }

    @Override
    public EntityCore getEntityCore() {
        return entityCore;
    }
}
