package net.theprogrammersworld.herobrine.api.util.storage;

public abstract class AbstractStorage implements IStorage {
    protected IDataKey root;

    public AbstractStorage() {

    }

    @Override
    public IDataKey getRoot() {
        if (root == null) {
            root = getKey("");
        }
        return root;
    }
}
