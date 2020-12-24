package net.theprogrammersworld.herobrine.test;

import net.minecraft.server.v1_16_R3.*;

public class TestEmptyNetworkHandler extends PlayerConnection {
    public TestEmptyNetworkHandler(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void sendPacket(Packet<?> packet) {

    }
}
