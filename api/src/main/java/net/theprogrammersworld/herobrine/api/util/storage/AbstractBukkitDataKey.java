package net.theprogrammersworld.herobrine.api.util.storage;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractBukkitDataKey extends AbstractDataKey {
    protected final ConfigurationSection root;

    public AbstractBukkitDataKey(ConfigurationSection root, String path) {
        super(path);
        this.root = root;
    }


    @Override
    public Object getObject(String key) {
        return root.get(getRelativeKey(key));
    }

    @Override
    public void setObject(String key, Object value) {
        root.set(key, value);
    }

    @Override
    public boolean keyExists(String key) {
        return root.isSet(key);
    }

    @Override
    public void removeKey(String key) {
        root.set(key, null);
    }

    @Override
    public List<IDataKey> getSubKeys() {
        ConfigurationSection head = root.getConfigurationSection(path);

        if (head == null) {
            return Collections.emptyList();
        }

        return head.getKeys(false).stream()
                .map(this::getRelative)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getValues() {
        ConfigurationSection head = root.getConfigurationSection(path);

        if (head == null) {
            return Collections.emptyMap();
        }

        return head.getValues(true);
    }
}
