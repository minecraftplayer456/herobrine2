package net.theprogrammersworld.herobrine;

import net.theprogrammersworld.herobrine.util.exceptions.ConfigException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigDB {
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

    }

    public void saveData(){

    }

    public void setDefaults(){

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
