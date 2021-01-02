package net.theprogrammersworld.herobrine.nms.network;

import java.net.SocketAddress;

import net.minecraft.server.v1_16_R3.EnumProtocolDirection;
import net.minecraft.server.v1_16_R3.NetworkManager;

public class EmptyNetworkManager extends NetworkManager {
    public EmptyNetworkManager() {
        super(EnumProtocolDirection.SERVERBOUND);

        channel = new EmptyChannel(null);
        socketAddress = new SocketAddress() {
            private static final long serialVersionUID = 8207338859896320185L;
        };
    }
}
