package net.theprogrammersworld.herobrine.api.network.wrapper;

import org.bukkit.entity.Player;

public interface IPacket {
    String getName();

    void broadcastPacket();

    void sendPacket(Player player);

    void receivePacket(Player player);
}
