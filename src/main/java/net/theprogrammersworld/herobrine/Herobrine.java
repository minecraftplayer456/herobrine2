package net.theprogrammersworld.herobrine;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Herobrine extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }
}
