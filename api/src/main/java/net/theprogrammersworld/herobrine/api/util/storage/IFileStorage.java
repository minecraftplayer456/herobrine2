package net.theprogrammersworld.herobrine.api.util.storage;

import java.io.File;

public interface IFileStorage extends IStorage {
    String getFileName();

    File getFile();
}
