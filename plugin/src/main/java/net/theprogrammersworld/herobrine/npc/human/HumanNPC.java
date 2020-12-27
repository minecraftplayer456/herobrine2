package net.theprogrammersworld.herobrine.npc.human;

import org.bukkit.Location;
import org.bukkit.World;

public class HumanNPC {
    private final HumanEntity entity;

    public HumanNPC(World world, String name) {
        entity = new HumanEntity(world, name);
    }

    public void spawn(Location location) {
        entity.spawn(location);
    }

    public HumanEntity getEntity() {
        return entity;
    }
}
