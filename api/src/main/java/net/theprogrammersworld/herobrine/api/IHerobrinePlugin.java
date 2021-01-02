package net.theprogrammersworld.herobrine.api;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import net.theprogrammersworld.herobrine.api.entity.IEntityCore;
import net.theprogrammersworld.herobrine.api.network.INetworkCore;
import net.theprogrammersworld.herobrine.api.nms.bridge.IBridge;
import net.theprogrammersworld.herobrine.api.util.message.IMessenger;
import net.theprogrammersworld.herobrine.api.util.settings.IHerobrineSettings;

/**
 * Interface implemented by the main herobrine plugin class
 * This class have all other core modules like NPCManager, ...
 *
 * @author MinecraftPlayer456
 * @version 0.1.0
 * @since 2.1.2
 */
public interface IHerobrinePlugin extends Plugin, Listener {

    /**
     * Returns IMessenger instance for logging to console, chat, ...
     * <p>
     * This should not be used for logging with herobrine extension plugins.
     * They should create a child messenger with IMessenger.createChild(name).
     *
     * @return IMessenger instance of herobrine plugin
     * @see IMessenger
     */
    IMessenger getMessenger();

    IHerobrineSettings getSettings();

    IBridge getBridge();

    INetworkCore getNetworkCore();

    IEntityCore getEntityCore();
}
