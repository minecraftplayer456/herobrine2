package net.theprogrammersworld.herobrine.api.entity;

import org.bukkit.GameMode;

import net.theprogrammersworld.herobrine.api.npc.profile.IProfile;

public interface IPlayerEntity extends IEntity {

    String getName();

    IProfile getProfile();

    GameMode getGameMode();
}
