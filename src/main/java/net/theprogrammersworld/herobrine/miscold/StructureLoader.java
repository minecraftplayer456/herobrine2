package net.theprogrammersworld.herobrine.miscold;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StructureLoader {

    private final InputStream inp;
    private final YamlConfiguration file;
    private int current = 0;
    private int length = 0;

    public StructureLoader(InputStream in) {
        inp = in;

        file = new YamlConfiguration();

        try {
            file.load(new InputStreamReader(inp, StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InvalidConfigurationException e) {

            e.printStackTrace();
        }

        try {
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Build(World world, int MainX, int MainY, int MainZ) {

        length = file.getInt("DATA.LENGTH") - 1;
        for (current = 0; current <= length; current++) {
            world.getBlockAt(MainX + file.getInt("DATA." + current + ".X"), MainY + file.getInt("DATA." + current + ".Y"), MainZ + file.getInt("DATA." + current + ".Z"))
                    .setType(Material.valueOf(file.getString("DATA." + current + ".MATERIAL")));
        }

    }

}
