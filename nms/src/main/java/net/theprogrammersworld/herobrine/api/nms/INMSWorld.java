package net.theprogrammersworld.herobrine.api.nms;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.theprogrammersworld.herobrine.api.nms.bridge.IWorld;

public interface INMSWorld extends IWorld {

    CraftWorld getCraftWorld();

    WorldServer getMinecraftWorld();
}
