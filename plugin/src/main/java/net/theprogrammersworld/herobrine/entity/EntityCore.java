package net.theprogrammersworld.herobrine.entity;

import net.minecraft.server.v1_16_R3.NetworkManager;
import org.bukkit.Location;

import com.comphenix.protocol.wrappers.EnumWrappers;

import net.theprogrammersworld.herobrine.api.entity.IEntityCore;
import net.theprogrammersworld.herobrine.api.entity.IPlayerEntity;
import net.theprogrammersworld.herobrine.api.network.INetworkCore;
import net.theprogrammersworld.herobrine.api.network.wrapper.IWrapperPlayServerPlayerInfo;
import net.theprogrammersworld.herobrine.api.nms.INMSServer;
import net.theprogrammersworld.herobrine.api.nms.INMSWorld;
import net.theprogrammersworld.herobrine.api.nms.bridge.IWorld;
import net.theprogrammersworld.herobrine.api.npc.profile.IProfile;
import net.theprogrammersworld.herobrine.nms.entity.PlayerEntity;
import net.theprogrammersworld.herobrine.nms.network.EmptyNetworkManager;

public class EntityCore implements IEntityCore {
    private final INMSServer server;
    private final INetworkCore networkCore;
    private final NetworkManager networkManager;

    public EntityCore(INMSServer server, INetworkCore networkCore) {
        this.server = server;
        this.networkCore = networkCore;

        networkManager = new EmptyNetworkManager();
    }

    @Override
    public IPlayerEntity createPlayerEntity(IWorld world, IProfile profile) {
        return new PlayerEntity(server, (INMSWorld) world, networkManager, profile);
    }

    @Override
    public void spawnPlayerEntity(IPlayerEntity entity, Location location) {
        final IWrapperPlayServerPlayerInfo playerInfoPacket = networkCore.createPlayerInfoPacket(entity, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        playerInfoPacket.broadcastPacket();

        entity.spawn(location);
    }

    @Override
    public void despawnPlayerEntity(IPlayerEntity entity) {
        final IWrapperPlayServerPlayerInfo playerInfoPacket = networkCore.createPlayerInfoPacket(entity, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
        playerInfoPacket.broadcastPacket();

        entity.despawn();
    }
}
