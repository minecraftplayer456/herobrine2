package net.theprogrammersworld.herobrine.miscold;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomID {

    private final String NAME;

    public CustomID(String _data) {
        NAME = _data;
    }

    public ItemStack getItemStack() {
        return new ItemStack(Material.matchMaterial(NAME));
    }

}
