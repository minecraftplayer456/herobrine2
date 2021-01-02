package net.theprogrammersworld.herobrine.api.nms;

import net.minecraft.server.v1_16_R3.MinecraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;

import net.theprogrammersworld.herobrine.api.nms.bridge.IServer;

public interface INMSServer extends IServer {

    CraftServer getCraftServer();

    MinecraftServer getMinecraftServer();
}
