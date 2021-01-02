package net.theprogrammersworld.herobrine.util.storage;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

import net.theprogrammersworld.herobrine.api.util.storage.AbstractBukkitDataKey;
import net.theprogrammersworld.herobrine.api.util.storage.IDataKey;

public class MemoryDataKey extends AbstractBukkitDataKey {
    public MemoryDataKey() {
        super(new MemoryConfiguration(), "");
    }

    private MemoryDataKey(ConfigurationSection root, String path) {
        super(root, path);
    }

    @Override
    protected IDataKey createRelative(String key) {
        return new MemoryDataKey(root, key);
    }
}
