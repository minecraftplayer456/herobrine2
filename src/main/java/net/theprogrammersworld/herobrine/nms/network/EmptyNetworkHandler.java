package net.theprogrammersworld.herobrine.nms.network;

public class EmptyNetworkHandler extends PlayerConnection {
    public EmptyNetworkHandler(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void sendPacket(Packet<?> packet) {

    }
}
