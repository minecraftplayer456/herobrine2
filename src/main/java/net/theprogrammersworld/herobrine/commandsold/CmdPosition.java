package net.theprogrammersworld.herobrine.commandsold;

import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class CmdPosition extends SubCommand {

    public CmdPosition(HerobrineOld plugin, Logger log) {
        super(plugin, log);
    }

    @Override
    public boolean execute(Player player, String[] args) {
        Location loc = plugin.HerobrineNPC.getBukkitEntity().getLocation();

        sendMessage(player, ChatColor.GREEN + "[Herobrine] Position - "
                + "World: " + loc.getWorld().getName()
                + ", Coordinates: (" + loc.getX() + ", " + loc.getY() + ", " + loc.getZ() + ")");

        return true;
    }

    @Override
    public String help() {
        return ChatColor.GREEN + "/herobrine position";
    }

    @Override
    public String helpDesc() {
        return ChatColor.GREEN + "Displays Herobrine's coordinates";
    }

}
