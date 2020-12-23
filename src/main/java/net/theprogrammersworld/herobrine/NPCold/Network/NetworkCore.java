package net.theprogrammersworld.herobrine.NPCold.Network;

import net.minecraft.server.v1_16_R3.EnumProtocolDirection;
import net.minecraft.server.v1_16_R3.NetworkManager;

public class NetworkCore extends NetworkManager {

    public NetworkCore() {
        super(EnumProtocolDirection.SERVERBOUND);
    }

    @Override
    public void a() {

    }

}
