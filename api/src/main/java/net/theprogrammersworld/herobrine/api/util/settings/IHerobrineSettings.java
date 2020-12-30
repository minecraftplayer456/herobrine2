package net.theprogrammersworld.herobrine.api.util.settings;

import net.theprogrammersworld.herobrine.api.util.message.MessageLevel;

public interface IHerobrineSettings {

    ISetting<MessageLevel> getMessageLevel();
}
