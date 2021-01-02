package net.theprogrammersworld.herobrine.api.nms.bridge;

import org.bukkit.World;

public interface IBridge {

    IServer getServer();

    IWorld getWorld(String name);

    IWorld getWorld(World world);
}
