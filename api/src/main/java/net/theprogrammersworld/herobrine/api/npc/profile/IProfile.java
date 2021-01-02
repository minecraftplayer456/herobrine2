package net.theprogrammersworld.herobrine.api.npc.profile;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

public interface IProfile {

    UUID getUUID();

    String getName();

    GameProfile getGameProfile();
}
