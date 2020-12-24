package net.theprogrammersworld.herobrine.NPCold.NMS;

import net.minecraft.server.v1_16_R3.DedicatedServer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BServer {

    private static BServer ins;
    private final Server server;
    private final HashMap<String, BWorld> worlds = new HashMap<String, BWorld>();
    private MinecraftServer mcServer;
    private CraftServer cServer;

    private BServer() {
        server = Bukkit.getServer();
        try {
            cServer = (CraftServer) server;
            mcServer = cServer.getServer();
        } catch (Exception ex) {
            Logger.getLogger("Minecraft").log(Level.SEVERE, null, ex);
        }
    }

    public static BServer getInstance() {
        if (ins == null) {
            ins = new BServer();
        }
        return ins;
    }

    public void stop() {
        mcServer.safeShutdown(true);
    }

    public void sendConsoleCommand(String cmd) {
        if (mcServer.isRunning()) {
            ((DedicatedServer) mcServer).issueCommand(cmd, mcServer.getServerCommandListener());
        }
    }

    public Logger getLogger() {
        return cServer.getLogger();
    }

    public List<WorldServer> getWorldServers() {
        List<WorldServer> worlds = new ArrayList<>();
        for (WorldServer world : mcServer.getWorlds())
            worlds.add(world);
        return worlds;
    }

    public Server getServer() {
        return server;
    }

    public BWorld getWorld(String worldName) {
        if (worlds.containsKey(worldName)) {
            return worlds.get(worldName);
        }
        BWorld w = new BWorld(ins.getServer().getWorld(worldName));
        worlds.put(worldName, w);
        return w;
    }

    public MinecraftServer getMCServer() {
        return mcServer;
    }

}
