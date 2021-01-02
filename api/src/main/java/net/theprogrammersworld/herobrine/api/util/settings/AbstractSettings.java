package net.theprogrammersworld.herobrine.api.util.settings;

import java.util.List;

import net.theprogrammersworld.herobrine.api.HerobrineApi;
import net.theprogrammersworld.herobrine.api.util.storage.IDataKey;
import net.theprogrammersworld.herobrine.api.util.storage.IStorage;

public abstract class AbstractSettings implements ISettings {

    private final IStorage config;
    private final IDataKey root;
    private List<ISetting<?>> settings;

    public AbstractSettings(IStorage config) {
        this.config = config;

        root = config.getKey("");
    }

    protected abstract List<ISetting<?>> gatherSettings();

    protected abstract void useSettings();

    @Override
    public void load() {
        config.load();

        HerobrineApi.getMessenger().debug("Loading settings");

        if (settings == null) {
            settings = gatherSettings();
        }

        settings.forEach(setting -> {
            if (root.keyExists(setting.getPath())) {
                setting.loadKey(root);
            } else {
                setting.saveKey(root);
            }
        });

        config.save();

        useSettings();
    }

    @Override
    public void reload() {
        load();
    }

    @Override
    public void save() {
        settings.forEach(setting -> setting.saveKey(root));

        config.save();
    }

    @Override
    public List<ISetting<?>> values() {
        return settings;
    }
}
