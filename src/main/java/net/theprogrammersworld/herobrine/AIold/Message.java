package net.theprogrammersworld.herobrine.AIold;

import net.theprogrammersworld.herobrine.HerobrineOld;
import org.bukkit.entity.Player;

import java.util.Random;

public class Message {

    public static void sendRandomMessage(Player player) {
        if (HerobrineOld.getPluginCore().getConfigDB().SendMessages == true) {

            int count = HerobrineOld.getPluginCore().getConfigDB().useMessages.size();

            Random randgen = new Random();
            int randmsg = randgen.nextInt(count);

            player.sendMessage("<Herobrine> " + HerobrineOld.getPluginCore().getConfigDB().useMessages.get(randmsg));

        }
    }

}
