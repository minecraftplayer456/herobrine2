package net.theprogrammersworld.herobrine.npc.human;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R3.*;
import net.theprogrammersworld.herobrine.network.EmptyNetworkHandler;
import net.theprogrammersworld.herobrine.network.EmptyNetworkManager;
import net.theprogrammersworld.herobrine.network.wrapper.WrapperPlayServerPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HumanEntity extends EntityPlayer {

    public HumanEntity(World world, String name) {
        super(((CraftServer) Bukkit.getServer()).getServer(),
                ((CraftWorld) world).getHandle(),
                new GameProfile(UUID.randomUUID(), name),
                new PlayerInteractManager(((CraftWorld) world).getHandle()));

        NetworkManager networkManager = new EmptyNetworkManager();
        playerConnection = new EmptyNetworkHandler(((CraftServer) Bukkit.getServer()).getServer(), networkManager, this);

        fauxSleeping = true;
        playerInteractManager.b(EnumGamemode.NOT_SET);
    }

    public void spawn(Location location) {
        setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        final WrapperPlayServerPlayerInfo playerInfoPacket = new WrapperPlayServerPlayerInfo();
        final PlayerInfoData infoData = new PlayerInfoData(WrappedGameProfile.fromHandle(getProfile()), 1, EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromHandle(getDisplayName()));
        final List<PlayerInfoData> infoDataList = new ArrayList<>();
        infoDataList.add(infoData);
        playerInfoPacket.setAction(EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        playerInfoPacket.setData(infoDataList);
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(playerInfoPacket.getHandle());

        WorldServer server = getWorldServer();
        server.addEntity(this);
    }
}
