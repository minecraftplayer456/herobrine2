package net.theprogrammersworld.herobrine.nms.network;

import net.minecraft.server.v1_16_R3.EnumProtocolDirection;
import net.minecraft.server.v1_16_R3.NetworkManager;
import net.minecraft.server.v1_16_R3.Packet;

import java.net.SocketAddress;

public class EmptyNetworkManager extends NetworkManager {
    public EmptyNetworkManager(EnumProtocolDirection enumprotocoldirection) {
        super(enumprotocoldirection);

        channel = new EmptyChannel(null);
        socketAddress = new SocketAddress() {
        };
    }

    @Override
    public void sendPacket(Packet<?> packet) {

    }

    @Override
    public boolean isConnected() {
        return true;
    }
}
