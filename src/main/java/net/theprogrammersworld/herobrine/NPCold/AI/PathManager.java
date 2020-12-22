package net.theprogrammersworld.herobrine.NPCold.AI;

import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.AIold.Core.CoreType;

public class PathManager {

	Path pathNow = null;

	public void setPath(Path path) {
		pathNow = path;
	}

	public void update() {
		if (pathNow != null && (HerobrineOld.getPluginCore().getAICore().getCoreTypeNow().equals(CoreType.ANY) ||
				HerobrineOld.getPluginCore().getAICore().getCoreTypeNow().equals(CoreType.RANDOM_POSITION))) {
			pathNow.update();
		}
	}

	public Path getPath() {
		return pathNow;
	}

}
