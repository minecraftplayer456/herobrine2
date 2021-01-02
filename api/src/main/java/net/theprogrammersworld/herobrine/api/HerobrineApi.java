package net.theprogrammersworld.herobrine.api;

import net.theprogrammersworld.herobrine.api.entity.IEntityCore;
import net.theprogrammersworld.herobrine.api.network.INetworkCore;
import net.theprogrammersworld.herobrine.api.nms.bridge.IBridge;
import net.theprogrammersworld.herobrine.api.util.message.IMessenger;
import net.theprogrammersworld.herobrine.api.util.settings.IHerobrineSettings;

/**
 * Interface to the herobrine api
 *
 * @author Minecraftplayer456
 * @version 0.1.0
 * @see IHerobrinePlugin
 * @since 2.1.2
 */
public final class HerobrineApi {
    private static IHerobrinePlugin impl;

    private HerobrineApi() {

    }

    /**
     * Gets the main herobrine plugin
     *
     * @return The main herobrine plugin
     * @see IHerobrinePlugin
     */
    public static IHerobrinePlugin getPlugin() {
        return getImplementation();
    }

    /**
     * Gets the IMessenger (Logger) to create child messenger for own logging
     *
     * @return the IMessenger (Logger)
     * @see IMessenger
     */
    public static IMessenger getMessenger() {
        return getImplementation().getMessenger();
    }

    public static IHerobrineSettings getSettings() {
        return getImplementation().getSettings();
    }

    public static IBridge getBridge() {
        return getImplementation().getBridge();
    }

    public static INetworkCore getNetworkCore() {
        return getImplementation().getNetworkCore();
    }

    public static IEntityCore getEntityCore() {
        return getImplementation().getEntityCore();
    }

    private static IHerobrinePlugin getImplementation() {
        if (impl == null) {
            throw new IllegalStateException("Herobrine api implementation is not set. The herobrine plugin isn't loaded");
        }
        return impl;
    }

    /**
     * Set the HerobrinePlugin implementation.
     * <p>
     * This can be only set once. It must always be set by the Herobrine main plugin class
     *
     * @param plugin HerobrinePlugin to be set as implementation
     * @see IHerobrinePlugin
     */
    public static void setImplementation(IHerobrinePlugin plugin) {
        if (impl != null) {
            throw new IllegalStateException("Herobrine api implementation can only be set once by the herobrine plugin");
        }
        impl = plugin;
    }
}
