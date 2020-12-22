package net.theprogrammersworld.herobrine.entityold;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;

public class EntityManager {
	
	private HashMap<Integer,CustomEntity> mobList = new HashMap<Integer,CustomEntity>();
	
	public void spawnCustomZombie(Location loc,MobType mbt){
		
		World world = loc.getWorld();
		net.minecraft.server.v1_16_R3.World mcWorld = ((org.bukkit.craftbukkit.v1_16_R3.CraftWorld) world).getHandle();
		CustomZombie zmb = new CustomZombie(mcWorld,loc,mbt);
		mcWorld.addEntity(zmb);
		mobList.put(Integer.valueOf(zmb.getBukkitEntity().getEntityId()),zmb);
		
	}
	
   public void spawnCustomSkeleton(Location loc,MobType mbt){
		
		World world = loc.getWorld();
		net.minecraft.server.v1_16_R3.World mcWorld = ((org.bukkit.craftbukkit.v1_16_R3.CraftWorld) world).getHandle();
		CustomSkeleton zmb = new CustomSkeleton(mcWorld,loc,mbt);
		mcWorld.addEntity(zmb);
		mobList.put(Integer.valueOf(zmb.getBukkitEntity().getEntityId()), zmb);
	}
	
	public boolean isCustomMob(int id){
		return mobList.containsKey(Integer.valueOf(id));
	}
	
	public CustomEntity getMobType(int id){
		return mobList.get(Integer.valueOf(id));
	}
	
	public void removeMob(int id){
		mobList.get(Integer.valueOf(id)).Kill();
		mobList.remove(Integer.valueOf(id));
	}
	
	public void removeAllMobs(){
		mobList.clear();
	}
	
	public void killAllMobs(){
		for(Map.Entry<Integer, CustomEntity> s : mobList.entrySet()){
			s.getValue().Kill();
		}
		removeAllMobs();
	}

}
