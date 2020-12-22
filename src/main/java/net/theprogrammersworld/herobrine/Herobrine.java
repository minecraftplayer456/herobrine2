package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.util.debug.Debug;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Herobrine extends JavaPlugin implements Listener {

    private ConfigDB configDB;
    private Debug debug;

    @Override
    public void onEnable() {
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
    public void onDisable() {
        //Save configuration files
        configDB.shutdown();
    }

    public Debug getDebug(){
        return debug;
    }

    public ConfigDB getConfigDB(){
        return configDB;
    }
}
