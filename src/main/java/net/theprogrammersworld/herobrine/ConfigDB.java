package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.util.debug.DebugLevel;
import net.theprogrammersworld.herobrine.util.exceptions.ConfigException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigDB {
    public DebugLevel logLevel;

    private YamlConfiguration config;

    private File configFile;

    public void startup(File dataDir) {
        configFile = new File(dataDir, "config.yml");

        config = new YamlConfiguration();
        //Values that do not exist get default values
        config.options().copyDefaults(true);

        setDefaults();

        if(!configFile.exists()){
            try{
                //No config files exists, make a new one and write it with default values
                loadData();
                saveFiles();
            }catch (Exception e){
                throw new ConfigException("Could not create new config file", e);
            }
        }else{
            //Load existing config files
            loadFiles();
            loadData();
        }
    }

    public void shutdown(){
        //Save config files
        saveData();
        saveFiles();
    }

    public void loadData(){
        logLevel = DebugLevel.valueOfName(config.getString("logLevel"));
    }

    public void saveData(){
        config.set("logLevel", logLevel.name);
    }

    public void setDefaults(){
        config.addDefault("logLevel", DebugLevel.Info.name);
    }

    public void loadFiles(){
        try{
            config.load(configFile);
        }catch (Exception e){
            throw new ConfigException("Could not load config file", e);
        }
    }

    public void saveFiles(){
        try{
            config.save(configFile);
        }catch (Exception e){
            throw new ConfigException("Could not save config file", e);
        }
    }
}
