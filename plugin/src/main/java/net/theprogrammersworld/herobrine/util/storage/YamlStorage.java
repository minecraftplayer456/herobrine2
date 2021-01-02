package net.theprogrammersworld.herobrine.util.storage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.theprogrammersworld.herobrine.api.util.storage.AbstractBukkitDataKey;
import net.theprogrammersworld.herobrine.api.util.storage.AbstractFileStorage;
import net.theprogrammersworld.herobrine.api.util.storage.IDataKey;

public class YamlStorage extends AbstractFileStorage {
    private final FileConfiguration config;

    public YamlStorage(File file) {
        this(file, null);
    }

    public YamlStorage(File file, String header) {
        super(file);
        config = new YamlConfiguration();

        if (header != null) {
            config.options().header(header);
        }
    }

    @Override
    protected void loadFile() throws IOException, InvalidConfigurationException {
        config.load(file);
    }

    @Override
    protected void saveFile() throws IOException {
        config.save(file);
    }

    @Override
    public IDataKey getKey(String root) {
        return new YamlKey(root);
    }

    public Map<?, ?> test() {
        return (Map<?, ?>) config.get("test");
    }

    public class YamlKey extends AbstractBukkitDataKey {

        protected YamlKey(String path) {
            super(config, path);
        }

        @Override
        protected IDataKey createRelative(String key) {
            return new YamlKey(key);
        }
    }
}
