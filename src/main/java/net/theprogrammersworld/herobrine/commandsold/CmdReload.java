package net.theprogrammersworld.herobrine.commandsold;

import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class CmdReload extends SubCommand {

    public CmdReload(HerobrineOld plugin, Logger log) {
        super(plugin, log);
    }

    @Override
    public boolean execute(Player player, String[] args) {

        plugin.getConfigDB().Reload();
        sendMessage(player, ChatColor.RED + "[Herobrine] Herobrine configuration file reloaded.");

        return true;
    }

    @Override
    public String help() {
        return ChatColor.GREEN + "/herobrine reload";
    }

    @Override
    public String helpDesc() {
        return ChatColor.GREEN + "Reloads the Herobrine configuration file";
    }

}
