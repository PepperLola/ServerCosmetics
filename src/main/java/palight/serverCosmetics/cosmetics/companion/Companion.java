package palight.serverCosmetics.cosmetics.companion;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.vecmath.Vector3d;

public class Companion {
    private Plugin serverCosmetics;
    private String name;
    private ItemStack itemStack;
    private Player owner;
    private Location location;

    public Companion(Plugin serverCosmetics, String name, ItemStack itemStack, Player owner) {
        this.serverCosmetics = serverCosmetics;
        this.name = name;
        this.itemStack = itemStack;
        this.owner = owner;
        this.location = owner.getLocation().add(0, 5, 0);
    }

    public Companion() {

    }

    public void update() {
        Location ownerLocation = owner.getLocation();

        double x_dist = ownerLocation.getBlockX() - location.getBlockX();
        double y_dist = ownerLocation.getBlockY() - location.getBlockY();
        double z_dist = ownerLocation.getBlockZ() - location.getBlockZ();

        double distance = Math.sqrt((x_dist * x_dist) + (y_dist * y_dist) + (z_dist * y_dist));
        double speed = 1 / Math.pow(0.75, distance);

        Vector3d distanceVector = new Vector3d(x_dist, y_dist, z_dist);

        if (distanceVector.length() != 0) {
            distanceVector.set(distanceVector.x / distanceVector.length(), distanceVector.y / distanceVector.length(), distanceVector.z / distanceVector.length());
        }

        distanceVector.scale(speed);

//        System.out.println("SPEED: " + speed);
//        System.out.println("DISTANCE: (" + x_dist + ", " + y_dist + ", " + z_dist + ")");
//        System.out.println("X: " + x_dist + " Y: " + y_dist + " Z: " + z_dist);

        location.add(distanceVector.x, distanceVector.y, distanceVector.z);

        //location = ownerLocation.add(0, 5, 0);

        display();
    }

    private void display() {
        owner.getWorld().spawnParticle(Particle.HEART, location, 50);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
