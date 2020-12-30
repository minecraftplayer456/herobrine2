package net.theprogrammersworld.herobrine.api.util.settings;

import net.theprogrammersworld.herobrine.api.util.storage.IDataKey;

public interface ISetting<T> {

    String getPath();

    T getValue();

    void loadKey(IDataKey root);

    void saveKey(IDataKey root);
}
