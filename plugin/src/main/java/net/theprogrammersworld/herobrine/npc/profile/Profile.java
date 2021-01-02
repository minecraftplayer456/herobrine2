package net.theprogrammersworld.herobrine.npc.profile;

import java.util.UUID;

import net.theprogrammersworld.herobrine.api.npc.profile.IProfile;

import com.mojang.authlib.GameProfile;

public class Profile implements IProfile {
    private final GameProfile profile;

    public Profile(String name) {
        this(UUID.randomUUID(), name);
    }

    public Profile(UUID uuid, String name) {
        profile = new GameProfile(uuid, name);
    }

    @Override
    public UUID getUUID() {
        return profile.getId();
    }

    @Override
    public String getName() {
        return profile.getName();
    }

    @Override
    public GameProfile getGameProfile() {
        return profile;
    }
}
