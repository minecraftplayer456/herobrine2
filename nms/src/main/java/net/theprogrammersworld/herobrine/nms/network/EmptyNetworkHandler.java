package net.theprogrammersworld.herobrine.nms.network;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.NetworkManager;
import net.minecraft.server.v1_16_R3.PlayerConnection;

import net.theprogrammersworld.herobrine.api.nms.INMSServer;

public class EmptyNetworkHandler extends PlayerConnection {
    public EmptyNetworkHandler(INMSServer server, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(server.getMinecraftServer(), networkmanager, entityplayer);
    }
}
