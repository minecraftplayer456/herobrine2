package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.api.HerobrineApi;
import net.theprogrammersworld.herobrine.api.IHerobrinePlugin;
import net.theprogrammersworld.herobrine.api.util.message.IMessenger;
import net.theprogrammersworld.herobrine.api.util.settings.IHerobrineSettings;
import net.theprogrammersworld.herobrine.util.message.Messenger;
import net.theprogrammersworld.herobrine.util.settings.HerobrineSettings;
import org.bukkit.plugin.java.JavaPlugin;

public class Herobrine extends JavaPlugin implements IHerobrinePlugin {

    private static IHerobrinePlugin plugin;

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
}
