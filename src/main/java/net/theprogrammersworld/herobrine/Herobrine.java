package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.npc.NPCCore;
import net.theprogrammersworld.herobrine.npc.human.HumanNPC;
import net.theprogrammersworld.herobrine.util.debug.Debug;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Herobrine extends JavaPlugin implements Listener {

    private ConfigDB configDB;
    private Debug debug;
    private NPCCore npcCore;

    @Override
    public void onEnable() {
        //Initializing debugging
        debug = new Debug();

        //Load configuration files
        configDB = new ConfigDB();
        configDB.startup(getDataFolder());

        //Set configured logLevel
        debug.setLevel(configDB.logLevel);

        //Initialize npc engine
        npcCore = new NPCCore();

        //Register this class as event listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            Player player = (Player) sender;

            HumanNPC npc = npcCore.createHumanNPC("Ho");
            npc.getEntity().spawn(player.getLocation());

            /*Bukkit.getScheduler().runTaskLater(this, () -> {
                npc.getEntity().destroy();
            }, 20 * 3L);*/

            Bukkit.getScheduler().runTaskTimer(this, () -> {
                final Location newLocation = npc.getEntity().getLocation().clone();
                npc.getEntity().moveTo(newLocation.add(1, 0, 0));
                npc.getEntity().setVelocity(new Vector(8000, 0, 0));
            }, 20 * 3L, 20 * 2L);

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
