package net.theprogrammersworld.herobrine.nms.bridge;

import org.bukkit.World;

import net.theprogrammersworld.herobrine.api.nms.bridge.IBridge;
import net.theprogrammersworld.herobrine.api.nms.bridge.IServer;
import net.theprogrammersworld.herobrine.api.nms.bridge.IWorld;

public class Bridge implements IBridge {
    private final IServer server;

    public Bridge() {
        server = new Server();
    }

    @Override
    public IServer getServer() {
        return server;
    }

    @Override
    public IWorld getWorld(String name) {
        return server.getWorld(name);
    }

    @Override
    public IWorld getWorld(World world) {
        return server.getWorld(world);
    }
}
