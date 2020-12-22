package net.theprogrammersworld.herobrine.AIold;

import net.theprogrammersworld.herobrine.HerobrineOld;

public abstract class Core {
	
	private final AppearType Appear;
	private final CoreType coreType;
	private CoreResult nowData = null;
	protected HerobrineOld PluginCore = null;
	
	public Core(CoreType cp,AppearType ap, HerobrineOld hb){
		this.coreType=cp;
		this.Appear=ap;
		this.PluginCore = hb;
	}
	
	public AppearType getAppear(){
		return Appear;
		}
	
	public CoreType getCoreType(){
		return coreType;
		}

	protected abstract CoreResult CallCore(Object[] data);
	
	public CoreResult RunCore(Object[] data){

		nowData=this.CallCore(data);
		if (nowData.getResult() && Appear == AppearType.APPEAR){
			HerobrineOld.getPluginCore().getAICore().setCoreTypeNow(this.coreType);
			
		}
		return nowData;
		}
	
	public enum CoreType{
		ATTACK,
		HAUNT,
		BOOK,
		BUILD_CAVE,
		BURY_PLAYER,
		DESTROY_TORCHES,
		GRAVEYARD,
		PYRAMID,
		RANDOM_POSITION,
		SIGNS,
		SOUNDF,
		TOTEM,
		ANY,
		START,
		TEMPLE,
		HEADS,
		RANDOM_SOUND,
		RANDOM_EXPLOSION,
		BURN,
		CURSE,
		STARE;
		
	}
	public enum AppearType{
		APPEAR,
		NORMAL,

	}
}
