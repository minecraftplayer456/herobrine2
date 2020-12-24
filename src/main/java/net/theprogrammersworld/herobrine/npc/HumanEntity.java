package net.theprogrammersworld.herobrine.npc;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.mojang.authlib.GameProfile;
import net.theprogrammersworld.herobrine.network.wrapper.WrapperPlayServerEntityTeleport;
import net.theprogrammersworld.herobrine.network.wrapper.WrapperPlayServerNamedEntitySpawn;
import net.theprogrammersworld.herobrine.network.wrapper.WrapperPlayServerPlayerInfo;
import net.theprogrammersworld.herobrine.network.wrapper.WrapperPlayServerRelEntityMove;
import net.theprogrammersworld.herobrine.util.exceptions.EntityException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HumanEntity {

    private final int entityId;
    private final GameProfile gameProfile;

    private Location location;

    public HumanEntity(String name) {
        entityId = (int) Math.ceil(Math.random() * 1000.0) + 2000;
        gameProfile = new GameProfile(UUID.randomUUID(), name);
        location = new Location(null, 0, -20, 0, 0, 0);
    }

    public void spawn(Location location) {
        this.location = location;
        Bukkit.getOnlinePlayers().forEach(this::sendEntityToPlayer);
    }

    public void sendEntityToPlayer(Player player) {
        final WrapperPlayServerNamedEntitySpawn namedEntitySpawnPacket = new WrapperPlayServerNamedEntitySpawn();
        namedEntitySpawnPacket.setEntityID(entityId);
        namedEntitySpawnPacket.setPlayerUUID(gameProfile.getId());
        namedEntitySpawnPacket.setX(location.getX());
        namedEntitySpawnPacket.setY(location.getY());
        namedEntitySpawnPacket.setZ(location.getZ());
        namedEntitySpawnPacket.setYaw(location.getYaw());
        namedEntitySpawnPacket.setPitch(location.getYaw());

        final WrapperPlayServerPlayerInfo playerInfoPacket = new WrapperPlayServerPlayerInfo();
        final List<PlayerInfoData> playerDataList = new ArrayList<>();
        final PlayerInfoData playerData = new PlayerInfoData(WrappedGameProfile.fromHandle(gameProfile),
                1, EnumWrappers.NativeGameMode.NOT_SET,
                WrappedChatComponent.fromText(gameProfile.getName()));
        playerDataList.add(playerData);
        playerInfoPacket.setData(playerDataList);
        playerInfoPacket.setAction(EnumWrappers.PlayerInfoAction.ADD_PLAYER);

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, playerInfoPacket.getHandle(), false);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, namedEntitySpawnPacket.getHandle(), false);
        } catch (InvocationTargetException e) {
            throw new EntityException("Could not send spawn packets", e);
        }
    }

    public void moveTo(Location location) {
        final double dx = this.location.getX() - location.getX();
        final double dy = this.location.getY() - location.getY();
        final double dz = this.location.getZ() - location.getZ();

        this.location = location;

        final WrapperPlayServerRelEntityMove entityMovePacket = new WrapperPlayServerRelEntityMove();
        entityMovePacket.setEntityID(entityId);
        entityMovePacket.setDx(dx);
        entityMovePacket.setDy(dy);
        entityMovePacket.setDz(dz);
        entityMovePacket.setYaw(location.getYaw());
        entityMovePacket.setPitch(location.getPitch());
        entityMovePacket.setOnGround(false);

        ProtocolLibrary.getProtocolManager().broadcastServerPacket(entityMovePacket.getHandle());
    }

    public void teleport(Location location) {
        this.location = location;

        final WrapperPlayServerEntityTeleport teleportPacket = new WrapperPlayServerEntityTeleport();
        teleportPacket.setEntityID(entityId);
        teleportPacket.setX(location.getX());
        teleportPacket.setY(location.getY());
        teleportPacket.setZ(location.getZ());
        teleportPacket.setYaw(location.getYaw());
        teleportPacket.setPitch(location.getPitch());

        ProtocolLibrary.getProtocolManager().broadcastServerPacket(teleportPacket.getHandle());
    }

    public int getEntityId() {
        return entityId;
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public Location getLocation() {
        return location;
    }
}
