package net.theprogrammersworld.herobrine.NPCold.Entity;

import com.mojang.authlib.GameProfile;
import net.theprogrammersworld.herobrine.NPCold.NMS.BWorld;
import net.theprogrammersworld.herobrine.NPCold.NPCCore;
import net.theprogrammersworld.herobrine.NPCold.Network.NetworkHandler;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

public class HumanEntity extends EntityPlayer {

    private CraftPlayer cplayer = null;

    public HumanEntity(final NPCCore npcCore, final BWorld world, final GameProfile s, final PlayerInteractManager playerInteractManager) {
        super(npcCore.getServer().getMCServer(), world.getWorldServer(), s, playerInteractManager);

        playerInteractManager.b(EnumGamemode.SURVIVAL);

        playerConnection = new NetworkHandler(npcCore, this);
        fauxSleeping = true;
    }

    @Override
    public void move(EnumMoveType x, Vec3D vec3d) {
        setPosition(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public boolean a(EntityHuman entity) {
        return super.a(entity);
    }

    @Override
    public void c(Entity entity) {
        super.c(entity);
    }

    @Override
    public CraftPlayer getBukkitEntity() {
        if (cplayer == null) {
            cplayer = new CraftPlayer((CraftServer) Bukkit.getServer(), this);
        }

        return cplayer;
    }

}
