package palight.serverCosmetics.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import palight.serverCosmetics.CustomListener;
import palight.serverCosmetics.ServerCosmetics;
import palight.serverCosmetics.enums.ElevatorModes;

public class PlayerJumpListener extends CustomListener {

    private ServerCosmetics plugin;

    public PlayerJumpListener(ServerCosmetics plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJump(PlayerStatisticIncrementEvent event) {
        if (!(event.getStatistic() == Statistic.JUMP)) return;

        Player eventPlayer = event.getPlayer();
        Location playerLocation = eventPlayer.getLocation();
        World eventWorld = eventPlayer.getWorld();

        ElevatorModes mode = eventPlayer.isSneaking() ? ElevatorModes.DOWN : ElevatorModes.UP;

        if (eventWorld.getBlockAt(playerLocation).getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            playerLocation = playerLocation.subtract(0, 1, 0);
            if (eventWorld.getBlockAt(playerLocation).getType() == Material.WHITE_CONCRETE) {
                elevator(eventPlayer, mode);
            }
        }
    }

    private void elevator(Player player, ElevatorModes mode) {

        final Material ELEVATOR_BLOCK = Material.HEAVY_WEIGHTED_PRESSURE_PLATE;

        Location playerLocation = player.getLocation();
        World world = player.getWorld();

        Location nextPlateLocation = playerLocation;

        switch (mode) {
            case UP:
                nextPlateLocation = player.getLocation().add(0, 1, 0);

                while (world.getBlockAt(nextPlateLocation).getType() != ELEVATOR_BLOCK) {
                    if (nextPlateLocation.getBlockY() > playerLocation.getBlockY() && nextPlateLocation.getBlockY() < world.getMaxHeight()) {
                        nextPlateLocation = nextPlateLocation.add(0, 1, 0);
                    } else {
                        return;
                    }
                }
                break;
            case DOWN:
                nextPlateLocation = player.getLocation().subtract(0, 1, 0);

                System.out.println(nextPlateLocation.getBlockY() + ", " + playerLocation.getBlockY());

                while (world.getBlockAt(nextPlateLocation).getType() != ELEVATOR_BLOCK) {
                    if (nextPlateLocation.getBlockY() < playerLocation.getBlockY() && nextPlateLocation.getBlockY() > 0) {
                        nextPlateLocation = nextPlateLocation.subtract(0, 1, 0);
                    } else {
                        return;
                    }
                }
                break;
        }

        player.teleport(nextPlateLocation);
    }
}
