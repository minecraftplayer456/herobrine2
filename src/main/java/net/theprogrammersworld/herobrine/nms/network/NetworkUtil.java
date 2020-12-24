package net.theprogrammersworld.herobrine.nms.network;

import net.minecraft.server.v1_16_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

import java.util.Arrays;

public class NetworkUtil {
    public static void sendPacket(final Packet<?>... packets) {
        Bukkit.getOnlinePlayers().stream()
                .map(player -> (CraftPlayer) player)
                .forEach(player -> {
                    Arrays.stream(packets)
                            .forEach(player.getHandle().playerConnection::sendPacket);
                });
    }
}
