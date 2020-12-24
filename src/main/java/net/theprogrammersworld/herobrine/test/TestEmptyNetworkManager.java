package net.theprogrammersworld.herobrine.test;

import net.minecraft.server.v1_16_R3.EnumProtocolDirection;
import net.minecraft.server.v1_16_R3.NetworkManager;
import net.minecraft.server.v1_16_R3.Packet;

import java.net.SocketAddress;

public class TestEmptyNetworkManager extends NetworkManager {
    public TestEmptyNetworkManager(EnumProtocolDirection enumprotocoldirection) {
        super(enumprotocoldirection);
        channel = new TestEmptyChannel(null);
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