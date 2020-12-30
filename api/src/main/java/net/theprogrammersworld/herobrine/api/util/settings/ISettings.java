package net.theprogrammersworld.herobrine.api.util.settings;

import java.util.List;

public interface ISettings {

    void load();

    void reload();

    void save();

    List<ISetting<?>> values();
}
