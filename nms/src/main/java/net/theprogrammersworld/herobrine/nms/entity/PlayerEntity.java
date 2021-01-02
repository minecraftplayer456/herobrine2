package net.theprogrammersworld.herobrine.nms.entity;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.EnumGamemode;
import net.minecraft.server.v1_16_R3.NetworkManager;
import net.minecraft.server.v1_16_R3.PlayerInteractManager;
import org.bukkit.GameMode;
import org.bukkit.Location;

import net.theprogrammersworld.herobrine.api.entity.AbstractEntity;
import net.theprogrammersworld.herobrine.api.entity.IPlayerEntity;
import net.theprogrammersworld.herobrine.api.nms.INMSServer;
import net.theprogrammersworld.herobrine.api.nms.INMSWorld;
import net.theprogrammersworld.herobrine.api.npc.profile.IProfile;
import net.theprogrammersworld.herobrine.nms.network.EmptyNetworkHandler;

public class PlayerEntity extends AbstractEntity implements IPlayerEntity {
    private final NMSPlayerEntity entity;
    private final IProfile profile;

    private Location location;

    public PlayerEntity(INMSServer server, INMSWorld world, NetworkManager networkManager, IProfile profile) {
        this.profile = profile;

        entity = new NMSPlayerEntity(server, world, profile);

        entity.playerConnection = new EmptyNetworkHandler(server, networkManager, entity);

        entity.fauxSleeping = true;
        entity.playerInteractManager.b(EnumGamemode.CREATIVE);
    }

    @Override
    public void spawn(Location location) {
        this.location = location;
        entity.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        entity.getWorldServer().addEntity(entity);
    }

    @Override
    public void despawn() {
        entity.getWorldServer().removeEntity(entity);
    }

    @Override
    public void teleport(Location location) {
        entity.teleportAndSync(location.getX(), location.getY(), location.getZ());
    }

    @Override
    public void moveTo(Location location) {

    }

    @Override
    public int getId() {
        return entity.getId();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public IProfile getProfile() {
        return profile;
    }

    @Override
    public GameMode getGameMode() {
        return entity.getBukkitEntity().getGameMode();
    }

    private static class NMSPlayerEntity extends EntityPlayer {
        public NMSPlayerEntity(INMSServer server, INMSWorld world, IProfile profile) {
            super(server.getMinecraftServer(),
                    world.getMinecraftWorld(),
                    profile.getGameProfile(),
                    new PlayerInteractManager(world.getMinecraftWorld()));
        }
    }
}
