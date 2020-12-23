package net.theprogrammersworld.herobrine.AIold.cores;

import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;
import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundF extends Core {

    public SoundF() {
        super(CoreType.SOUNDF, AppearType.NORMAL, HerobrineOld.getPluginCore());
    }

    public CoreResult CallCore(Object[] data) {
        return playRandom((Player) data[0]);
    }

    public CoreResult playRandom(Player player) {

        Sound[] sounds = {
                Sound.ENTITY_GHAST_SCREAM,
                Sound.ENTITY_WITHER_DEATH,
                Sound.ENTITY_WITHER_HURT,
                Sound.ENTITY_BAT_HURT,
                Sound.ENTITY_PLAYER_BREATH,
                Sound.ENTITY_PLAYER_HURT,
                Sound.BLOCK_IRON_DOOR_OPEN,
                Sound.BLOCK_IRON_DOOR_CLOSE
        };


        player.playSound(player.getLocation(), sounds[Utils.getRandomGen().nextInt(sounds.length)], (float) 0.75, (float) 0.75);

        return new CoreResult(true, "SoundF is starting.");
    }

}
