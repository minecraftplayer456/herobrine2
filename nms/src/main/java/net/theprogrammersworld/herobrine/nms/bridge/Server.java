package net.theprogrammersworld.herobrine.nms.bridge;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_16_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;

import net.theprogrammersworld.herobrine.api.HerobrineApi;
import net.theprogrammersworld.herobrine.api.nms.INMSServer;
import net.theprogrammersworld.herobrine.api.nms.bridge.IWorld;

public class Server implements INMSServer {
    private final CraftServer craftServer;
    private final MinecraftServer server;
    private final Map<String, IWorld> worlds;

    public Server() {
        craftServer = (CraftServer) Bukkit.getServer();
        server = craftServer.getServer();

        worlds = new HashMap<>();
    }

    @Override
    public IWorld getWorld(String name) {
        if (!worlds.containsKey(name)) {
            org.bukkit.World world = craftServer.getWorld(name);

            if (world == null) {
                throw HerobrineApi.getMessenger().throwing("World {} does not exist", new IllegalArgumentException("World does not exist"), name);
            }

            worlds.put(name, new World(world));
        }
        return worlds.get(name);
    }

    @Override
    public IWorld getWorld(org.bukkit.World world) {
        return getWorld(world.getName());
    }

    @Override
    public Map<String, IWorld> getWorlds() {
        return worlds;
    }

    @Override
    public org.bukkit.Server getServer() {
        return craftServer;
    }

    @Override
    public CraftServer getCraftServer() {
        return craftServer;
    }

    @Override
    public MinecraftServer getMinecraftServer() {
        return server;
    }
}
