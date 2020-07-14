package palight.serverCosmetics.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import palight.serverCosmetics.CustomListener;
import palight.serverCosmetics.ServerCosmetics;
import palight.serverCosmetics.items.CosmeticItem;

import java.util.Objects;

public class PlayerListener extends CustomListener {

    private ServerCosmetics plugin;

    public PlayerListener(ServerCosmetics plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.getCompanionManager().removeCompanion(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        for (CosmeticItem item : plugin.getCosmeticItems()) {
            item.onInteract(event);
        }
    }
}
