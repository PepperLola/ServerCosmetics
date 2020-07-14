package palight.serverCosmetics.items;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import palight.serverCosmetics.ServerCosmetics;

import java.util.Objects;

public abstract class CosmeticItem {

    protected ServerCosmetics plugin;
    private Material material;
    private Action[] actions;

    public CosmeticItem(ServerCosmetics plugin, Material material, Action[] actions) {
        this.plugin = plugin;
        this.material = material;
        this.actions = actions;
    }

    public boolean validateInteraction(PlayerInteractEvent event) {
        if (event.getItem() == null) return false;
        return (event.getItem().getType() == this.material) && (ArrayUtils.contains(actions, event.getAction()));
    }

    public abstract void onInteract(PlayerInteractEvent event);
}
