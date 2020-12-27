package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.api.HerobrineApi;
import net.theprogrammersworld.herobrine.api.HerobrinePlugin;
import net.theprogrammersworld.herobrine.api.util.IMessenger;
import net.theprogrammersworld.herobrine.api.util.MessageLevel;
import net.theprogrammersworld.herobrine.util.Messenger;
import org.bukkit.plugin.java.JavaPlugin;

public class Herobrine extends JavaPlugin implements HerobrinePlugin {

    private IMessenger messenger;

    @Override
    public void onEnable() {
        //Initialize messenger
        messenger = new Messenger("Herobrine");

        //TODO Set through config file
        messenger.setLevel(MessageLevel.TRACE);

        //Initialize api
        HerobrineApi.setImplementation(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public IMessenger getMessenger() {
        return messenger;
    }

    /*private static Herobrine plugin;

    private ConfigDB configDB;
    private Debug debug;

    public static Herobrine getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        //Initializing debugging
        debug = new Debug();

        //Load configuration files
        configDB = new ConfigDB();
        configDB.startup(getDataFolder());

        //Set configured logLevel
        debug.setLevel(configDB.logLevel);

        //Register this class as event listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            Player player = (Player) sender;

            HumanEntity entity = new HumanEntity(player.getWorld(), "Herobrine");
            entity.spawn(player.getLocation());

            sender.sendMessage("Created npc!");
        }
        return false;
    }

    @Override
    public void onDisable() {
        //Save configuration files
        configDB.shutdown();
    }

    public Debug getDebug() {
        return debug;
    }

    public ConfigDB getConfigDB() {
        return configDB;
    }*/
}
