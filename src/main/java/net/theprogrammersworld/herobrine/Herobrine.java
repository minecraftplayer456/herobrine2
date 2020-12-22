package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.util.Debug;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Herobrine extends JavaPlugin implements Listener {

    private Debug debug;

    @Override
    public void onEnable() {
        //Initializing debugging
        debug = new Debug();

        //Register this class as event listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }
}
