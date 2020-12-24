package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.test.TestNPC;
import net.theprogrammersworld.herobrine.util.debug.Debug;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            Player player = (Player) sender;

            TestNPC npc = new TestNPC("Hello World!", player.getWorld());
            npc.spawn(player.getLocation());

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
    }
}
