package net.theprogrammersworld.herobrine.api.entity;

import org.bukkit.Location;

import net.theprogrammersworld.herobrine.api.nms.bridge.IWorld;
import net.theprogrammersworld.herobrine.api.npc.profile.IProfile;

public interface IEntityCore {
    IPlayerEntity createPlayerEntity(IWorld world, IProfile profile);

    void spawnPlayerEntity(IPlayerEntity entity, Location location);

    void despawnPlayerEntity(IPlayerEntity entity);
}
