package palight.serverCosmetics.timers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import palight.serverCosmetics.Timer;

public class TrampolineTimer extends Timer {
    private Plugin serverCosmetics;

    public TrampolineTimer(Plugin serverCosmetics) {
        super(serverCosmetics);
        this.serverCosmetics = serverCosmetics;
    }

    @Override
    public BukkitRunnable getRunnable() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer.isSneaking()) return;
                    Location blockLocation = onlinePlayer.getLocation().subtract(0, 1, 0);
                    World playerWorld = onlinePlayer.getWorld();
                    Material blockMaterial = playerWorld.getBlockAt(blockLocation).getType();

                    if (blockMaterial == Material.BLACK_CONCRETE) {
                        int bounceLevel = 0;
                        blockLocation = blockLocation.subtract(0, 1, 0);

                        while (playerWorld.getBlockAt(blockLocation).getType() == Material.SLIME_BLOCK) {
                            bounceLevel++;
                            blockLocation = blockLocation.subtract(0, 1, 0);
                        }

                        Vector playerVelocity = onlinePlayer.getVelocity();
                        if (bounceLevel != 0) {
                            onlinePlayer.setVelocity(playerVelocity.setY(bounceLevel));
                        }
                    }
                }
            }
        };
    }
}
