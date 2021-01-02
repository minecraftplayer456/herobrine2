package net.theprogrammersworld.herobrine.api.entity;

import org.bukkit.Location;

public interface IEntity {
    void spawn(Location location);

    void despawn();

    void teleport(Location location);

    void moveTo(Location location);

    int getId();

    Location getLocation();
}
