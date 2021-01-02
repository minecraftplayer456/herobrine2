package net.theprogrammersworld.herobrine.api.nms.bridge;

import java.util.Map;

import org.bukkit.Server;
import org.bukkit.World;

public interface IServer {

    IWorld getWorld(String name);

    IWorld getWorld(World world);

    Map<String, IWorld> getWorlds();

    Server getServer();
}
