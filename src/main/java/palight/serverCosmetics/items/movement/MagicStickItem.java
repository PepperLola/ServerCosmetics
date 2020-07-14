package palight.serverCosmetics.items.movement;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import palight.serverCosmetics.ServerCosmetics;
import palight.serverCosmetics.items.CosmeticItem;

public class MagicStickItem extends CosmeticItem {
    public MagicStickItem(ServerCosmetics plugin) {
        super(plugin, Material.STICK, new Action[]{Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR});
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if (!validateInteraction(event)) return;

        Player player = event.getPlayer();
        player.setVelocity(player.getVelocity().add(player.getEyeLocation().getDirection().multiply(-5)));

        new BukkitRunnable() {

            int degrees = 0;

            @Override
            public void run() {
                degrees %= 360;
                player.getWorld().spawnParticle(Particle.DRIP_LAVA, player.getLocation().add(Math.cos(degrees) * 2, -1, Math.sin(degrees) * 2), 10);
                degrees ++;
            }
        }.runTaskTimer(plugin, 0, 5);
    }
}
