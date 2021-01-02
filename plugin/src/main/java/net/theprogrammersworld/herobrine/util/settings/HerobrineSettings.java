package net.theprogrammersworld.herobrine.util.settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.theprogrammersworld.herobrine.Herobrine;
import net.theprogrammersworld.herobrine.api.util.message.MessageLevel;
import net.theprogrammersworld.herobrine.api.util.settings.AbstractSetting;
import net.theprogrammersworld.herobrine.api.util.settings.AbstractSettings;
import net.theprogrammersworld.herobrine.api.util.settings.IHerobrineSettings;
import net.theprogrammersworld.herobrine.api.util.settings.ISetting;
import net.theprogrammersworld.herobrine.util.storage.YamlStorage;

public class HerobrineSettings extends AbstractSettings implements IHerobrineSettings {

    private final AbstractSetting.EnumSetting<MessageLevel> MESSAGE_LEVEL = new AbstractSetting.EnumSetting<>("message.level", MessageLevel.INFO, MessageLevel.class);

    public HerobrineSettings(File dir) {
        super(new YamlStorage(new File(dir, "config.yml"), "Herobrine Configuration"));
    }

    @Override
    protected List<ISetting<?>> gatherSettings() {
        List<ISetting<?>> settings = new ArrayList<>();
        settings.add(MESSAGE_LEVEL);
        return settings;
    }

    @Override
    protected void useSettings() {
        //Set message level
        Herobrine.getPlugin().getMessenger().setLevel(MESSAGE_LEVEL.getValue());

        if (MESSAGE_LEVEL.getValue().getLevel() <= MessageLevel.DEBUG.getLevel()) {
            Herobrine.getPlugin().getMessenger().debug("Setting message level to {}", MESSAGE_LEVEL.getValue().toString());
        }
    }

    @Override
    public ISetting<MessageLevel> getMessageLevel() {
        return MESSAGE_LEVEL;
    }
}
