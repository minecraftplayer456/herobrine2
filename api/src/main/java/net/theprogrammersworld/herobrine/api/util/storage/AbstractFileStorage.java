package net.theprogrammersworld.herobrine.api.util.storage;

import net.theprogrammersworld.herobrine.api.HerobrineApi;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public abstract class AbstractFileStorage extends AbstractStorage implements IFileStorage {
    protected final File file;
    protected final String fileName;

    public AbstractFileStorage(File file) {
        super();
        this.file = file;
        fileName = file.getName();
    }

    protected void createFile() {
        try {
            HerobrineApi.getMessenger().debug("Creating storage file: {}", fileName);
            file.getCanonicalFile().getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            HerobrineApi.getMessenger().catching("Could not create storage file: {}", e, fileName);
        }
    }

    protected abstract void loadFile() throws IOException, InvalidConfigurationException;

    protected abstract void saveFile() throws IOException;

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public boolean load() {
        if (!file.exists()) {
            createFile();
        }

        try {
            HerobrineApi.getMessenger().debug("Load storage file: {}", fileName);
            loadFile();
            return true;
        } catch (IOException | InvalidConfigurationException e) {
            HerobrineApi.getMessenger().catching("Could not load storage file: {}", e, fileName);
            return false;
        }
    }

    @Override
    public boolean save() {
        try {
            HerobrineApi.getMessenger().debug("Save storage file: {}", fileName);
            saveFile();
            return true;
        } catch (IOException e) {
            HerobrineApi.getMessenger().catching("Could not save storage file: {}", e, fileName);
            return false;
        }
    }
}
