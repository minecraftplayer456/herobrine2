package net.theprogrammersworld.herobrine.api;

import net.theprogrammersworld.herobrine.api.util.IMessenger;

/**
 * Interface to the herobrine api
 *
 * @author Minecraftplayer456
 * @version 0.1.0
 * @see HerobrinePlugin
 * @since 2.1.2
 */
public final class HerobrineApi {
    private static HerobrinePlugin impl;

    private HerobrineApi() {

    }

    /**
     * Gets the main herobrine plugin
     *
     * @return The main herobrine plugin
     * @see HerobrinePlugin
     */
    public static HerobrinePlugin getPlugin() {
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

    private static HerobrinePlugin getImplementation() {
        if (impl == null) {
            throw new IllegalStateException("Herobrine api implementation is not set. The herobrine plugin isn't loaded");
        }
        return impl;
    }

    /**
     * Set the HerobrinePlugin implementation.
     * <p>
     * This can be only set once. It must allways be set by the Herobrine main plugin class
     *
     * @param plugin HerobrinePlugin to be set as implementation
     * @see HerobrinePlugin
     */
    public static void setImplementation(HerobrinePlugin plugin) {
        if (impl != null) {
            throw new IllegalStateException("Herobrine api implementation can only be set once by the herobrine plugin");
        }
        impl = plugin;
    }
}
