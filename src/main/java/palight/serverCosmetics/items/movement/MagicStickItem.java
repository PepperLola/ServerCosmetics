package palight.serverCosmetics.items.movement;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import palight.serverCosmetics.ServerCosmetics;
import palight.serverCosmetics.items.CosmeticItem;
import palight.serverCosmetics.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class MagicStickItem extends CosmeticItem {

    private List<Vector> particleTemplate = new ArrayList<>();

    public MagicStickItem(ServerCosmetics plugin) {
        super(plugin, Material.STICK, new Action[]{Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR});

//        for (int degrees = 0; degrees < 360; degrees += 15) {
//
//            double x = 1;
//            double y = Math.sin(degrees);
//            double z = Math.cos(degrees);
//
//            Vector particleVector = new Vector(x, y, z);
//            particleTemplate.add(particleVector);
//        }
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if (!validateInteraction(event)) return;

        final Player player = event.getPlayer();

        player.setVelocity(player.getVelocity().add(new Vector().copy(player.getEyeLocation().getDirection()).multiply(-5)));
//        System.out.println(lookVector.toString());

        BukkitRunnable particleRunnable = new BukkitRunnable() {
            int degrees = 0;

            Location eyeLocation = player.getEyeLocation();
            final Vector lookVector = eyeLocation.getDirection();

            @Override
            public void run() {
                eyeLocation = player.getEyeLocation();
                if (degrees >= 360) {
                    this.cancel();
                }

                double scale = 4;
                double distance = 2;

                double x = distance;
                double y = MathUtil.sinDeg(degrees) * scale;
                double z = MathUtil.cosDeg(degrees) * scale;

                Vector particleVector = new Vector(x, y, z);

                double yaw = MathUtil.degToRad(eyeLocation.getYaw()) + (Math.PI / 2);
                double pitch = MathUtil.degToRad(eyeLocation.getPitch());

                Vector multiplied = MathUtil.vectorMatrixMultiply(particleVector, new Vector(0, 0, 1), -pitch);
                multiplied = MathUtil.vectorMatrixMultiply(multiplied, new Vector(0, 1, 0), -yaw);

                Location particleLocation = new Location(eyeLocation.getWorld(), eyeLocation.getX() + lookVector.getX() * 5, eyeLocation.getY() + lookVector.getY() * 5, eyeLocation.getZ() + lookVector.getZ() * 5).add(multiplied);

                player.getWorld().spawnParticle(Particle.HEART, particleLocation, 10);

                degrees += 15;
            }
        };

        particleRunnable.runTaskTimer(plugin, 0, 1);
    }
}
