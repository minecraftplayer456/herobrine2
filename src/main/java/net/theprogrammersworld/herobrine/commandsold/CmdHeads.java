package net.theprogrammersworld.herobrine.commandsold;

import net.theprogrammersworld.herobrine.AIold.Core.CoreType;
import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class CmdHeads extends SubCommand {

    public CmdHeads(HerobrineOld plugin, Logger log) {
        super(plugin, log);
    }

    @Override
    public boolean execute(Player player, String[] args) {

        if (args.length > 1) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                sendMessage(player, ChatColor.RED + "[Herobrine] " + args[1] + " cannot be haunted with heads because they are offline.");
                return true;
            }

            if (!target.isOnline()) {
                sendMessage(player, ChatColor.RED + "[Herobrine] " + args[1] + " cannot be haunted with heads because they are offline.");
                return true;
            }

            if (!plugin.getSupport().checkBuild(target.getLocation())) {
                sendMessage(player, ChatColor.RED + "[Herobrine] " + args[1] + " cannot be haunted with heads because they are in a secure area.");
                return true;
            }

            Object[] data = {args[1]};
            sendMessage(player, ChatColor.RED + "[Herobrine] " + plugin.getAICore().getCore(CoreType.HEADS).RunCore(data).getResultString());

            return true;
        }

        return false;
    }

    @Override
    public String help() {
        return ChatColor.GREEN + "/herobrine heads <player>";
    }

    @Override
    public String helpDesc() {
        return ChatColor.GREEN + "Spawns the specified player's heads near the specified player";
    }

}
