package net.theprogrammersworld.herobrine.nms.network;

import net.minecraft.server.v1_16_R3.*;

public class EmptyNetworkHandler extends PlayerConnection {
    public EmptyNetworkHandler(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void sendPacket(Packet<?> packet) {

    }
}
