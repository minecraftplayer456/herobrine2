package net.theprogrammersworld.herobrine.test;

public class TestEmptyNetworkHandler extends PlayerConnection {
    public TestEmptyNetworkHandler(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void sendPacket(Packet<?> packet) {

    }
}
