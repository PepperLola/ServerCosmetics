package palight.serverCosmetics.timers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import palight.serverCosmetics.cosmetics.companion.CompanionManager;

public class TrampolineTimer extends Timer {
    private Plugin serverCosmetics;
    private CompanionManager companionManager;

    public TrampolineTimer(Plugin serverCosmetics, CompanionManager companionManager) {
        super(serverCosmetics);
        this.serverCosmetics = serverCosmetics;
        this.companionManager = companionManager;
    }

    @Override
    public BukkitRunnable getRunnable() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    Location blockLocation = onlinePlayer.getLocation().subtract(0, 1, 0);
                    World playerWorld = onlinePlayer.getWorld();
                    Material blockMaterial = playerWorld.getBlockAt(blockLocation).getType();

                    if (blockMaterial == Material.BLACK_CONCRETE) {
                        Vector playerVelocity = onlinePlayer.getVelocity();
                        onlinePlayer.setVelocity(playerVelocity.setY(5));
                    }
                }
            }
        };
    }
}
