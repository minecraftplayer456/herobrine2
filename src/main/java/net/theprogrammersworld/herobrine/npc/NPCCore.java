package net.theprogrammersworld.herobrine.npc;

import net.theprogrammersworld.herobrine.npc.human.HumanNPC;

import java.util.ArrayList;
import java.util.List;

public class NPCCore {
    private final List<HumanNPC> npcs;

    public NPCCore() {
        npcs = new ArrayList<>();
    }

    public HumanNPC createHumanNPC(String name) {
        final HumanNPC npc = new HumanNPC(name);
        npcs.add(npc);
        return npc;
    }

    public List<HumanNPC> getNPCs() {
        return npcs;
    }
}
