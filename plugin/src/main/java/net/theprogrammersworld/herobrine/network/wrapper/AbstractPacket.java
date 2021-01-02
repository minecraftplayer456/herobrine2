package net.theprogrammersworld.herobrine.network.wrapper;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;

import net.theprogrammersworld.herobrine.api.HerobrineApi;
import net.theprogrammersworld.herobrine.api.network.wrapper.IPacket;

public abstract class AbstractPacket extends PacketContainer implements IPacket {

    private final String name;

    protected AbstractPacket(String name, PacketType type) {
        super(type);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void broadcastPacket() {
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(this);
    }

    @Override
    public void sendPacket(Player player) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, this);
        } catch (InvocationTargetException e) {
            HerobrineApi.getMessenger().catching("Could not send packet {} to {}", e, name, player.getName());
        }
    }

    @Override
    public void receivePacket(Player player) {
        try {
            ProtocolLibrary.getProtocolManager().recieveClientPacket(player, this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            HerobrineApi.getMessenger().catching("Could not receive packet {} from {}", e, name, player.getName());
        }
    }
}