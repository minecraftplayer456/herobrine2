package net.theprogrammersworld.herobrine.listenersold;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.AIold.Core.CoreType;

public class WorldListener implements Listener{

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event){
		if (event.isNewChunk()){

			World world = event.getWorld();
			
			if (HerobrineOld.getPluginCore().getConfigDB().useWorlds.contains(world.getName())){

			 if (HerobrineOld.getPluginCore().getConfigDB().BuildTemples==true){
				 int templeChance = HerobrineOld.getPluginCore().getConfigDB().BuildTempleOnChunkPercentage;
				 if (new Random().nextInt(100) + 1 <= templeChance){
					 Object[] data = {event.getChunk()};
					 HerobrineOld.getPluginCore().getAICore().getCore(CoreType.TEMPLE).RunCore(data);
				 }
			 }
			 
			 if (HerobrineOld.getPluginCore().getConfigDB().BuildPyramids==true){
				 int pyramidChance = HerobrineOld.getPluginCore().getConfigDB().BuildPyramidOnChunkPercentage;
				 if (new Random().nextInt(100) + 1 <= pyramidChance){
					 Object[] data = {event.getChunk()};
				     HerobrineOld.getPluginCore().getAICore().getCore(CoreType.PYRAMID).RunCore(data);
				 }
			 }
		}
	}
}
	
}
