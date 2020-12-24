package net.theprogrammersworld.herobrine.test;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestNPC extends EntityPlayer {
    public TestNPC(String name, World world) {
        super(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) world).getHandle(), new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(((CraftWorld) world).getHandle()));

        try (final Socket ignored = new TestEmptySocket()) {
            NetworkManager conn = new TestEmptyNetworkManager(EnumProtocolDirection.CLIENTBOUND);
            this.playerConnection = new TestEmptyNetworkHandler(this.server, conn, this);
            conn.setPacketListener(this.playerConnection);
        } catch (final IOException exception) {
            exception.printStackTrace();
        }

        playerInteractManager.b(EnumGamemode.CREATIVE);

        addEntityToWorld(this);

        Player player = getBukkitEntity();
        player.setGameMode(GameMode.CREATIVE);
        player.setSleepingIgnored(true);
        addOrRemoveFromPlayerList(this, false);
    }

    public void spawn(Location location) {
        addOrRemoveFromPlayerList(this, false);
        sendTabListAdd(this);
        sendPacket(new PacketPlayOutNamedEntitySpawn(this));
        getBukkitEntity().teleport(location);
        sendPositionUpdate(this);
        sendPacket(
                new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, this)
        );
    }

    public void sendPacket(final Packet<?>... packets) {
        Bukkit.getOnlinePlayers().stream()
                .map(player -> (CraftPlayer) player)
                .forEach(player -> {
                    Arrays.stream(packets).forEach(player.getHandle().playerConnection::sendPacket);
                });
    }

    public void sendPositionUpdate(EntityPlayer player) {
        sendPacket(new PacketPlayOutEntityTeleport(player));
    }

    void addOrRemoveFromPlayerList(final EntityPlayer player, final boolean remove) {
        if (player.world == null) {
            return;
        }
        if (remove) {
            player.world.getPlayers().remove(player);
            player.server.getPlayerList().players.remove(player);
        } else if (!player.world.getPlayers().contains(player)) {
            ((List) player.world.getPlayers()).add(player);
            player.server.getPlayerList().players.add(player);
        }
    }

    void sendTabListAdd(final EntityPlayer player) {
        sendPacket(
                new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, player)
        );
    }

    void addEntityToWorld(final EntityPlayer player) {
        int viewDistance = -1;
        PlayerChunkMap chunkMap = null;
        try {
            final Field viewDistanceField = PlayerChunkMap.class.getDeclaredField("viewDistance");
            final boolean access = viewDistanceField.isAccessible();
            viewDistanceField.setAccessible(true);
            try {
                chunkMap = ((ChunkProviderServer) player.world.getChunkProvider()).playerChunkMap;
                viewDistance = (int) viewDistanceField.get(chunkMap);
                viewDistanceField.set(chunkMap, -1);
            } catch (final Throwable e) {
                e.printStackTrace();
            }
            player.world.addEntity(player);
            try {
                if (chunkMap != null) {
                    viewDistanceField.set(chunkMap, viewDistance);
                }
            } catch (final Throwable e) {
                e.printStackTrace();
            }
            viewDistanceField.setAccessible(access);
        } catch (final NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
