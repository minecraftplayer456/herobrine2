package net.theprogrammersworld.herobrine.nms.bridge;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.theprogrammersworld.herobrine.api.nms.INMSWorld;

public class World implements INMSWorld {
    private final CraftWorld craftWorld;
    private final WorldServer server;

    public World(org.bukkit.World world) {
        craftWorld = (CraftWorld) world;
        server = craftWorld.getHandle();
    }

    @Override
    public String getName() {
        return craftWorld.getName();
    }

    @Override
    public org.bukkit.World getWorld() {
        return craftWorld;
    }

    @Override
    public CraftWorld getCraftWorld() {
        return craftWorld;
    }

    @Override
    public WorldServer getMinecraftWorld() {
        return server;
    }
}
