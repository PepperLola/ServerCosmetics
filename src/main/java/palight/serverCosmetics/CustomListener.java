package palight.serverCosmetics;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class CustomListener implements Listener {
    private ServerCosmetics plugin;

    public CustomListener(ServerCosmetics plugin){ this.plugin = plugin; }

    public void enable() {
        Bukkit.getLogger().info("ENABLING LISTENER: " + this.toString());
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void disable() {

    }
}
