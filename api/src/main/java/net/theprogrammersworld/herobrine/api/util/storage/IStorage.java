package net.theprogrammersworld.herobrine.api.util.storage;

//TODO getRootKey
public interface IStorage {
    IDataKey getRoot();

    IDataKey getKey(String root);

    boolean load();

    boolean save();
}
