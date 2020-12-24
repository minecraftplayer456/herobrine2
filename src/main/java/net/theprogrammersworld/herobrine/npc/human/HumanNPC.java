package net.theprogrammersworld.herobrine.npc.human;

public class HumanNPC {
    private final HumanEntity entity;

    public HumanNPC(String name) {
        entity = new HumanEntity(name);
    }

    public HumanEntity getEntity() {
        return entity;
    }
}
