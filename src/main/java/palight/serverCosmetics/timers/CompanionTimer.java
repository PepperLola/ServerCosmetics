package palight.serverCosmetics.timers;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import palight.serverCosmetics.Timer;
import palight.serverCosmetics.cosmetics.companion.Companion;
import palight.serverCosmetics.cosmetics.companion.CompanionManager;

import java.util.ArrayList;
import java.util.List;

public class CompanionTimer extends Timer {

    private Plugin serverCosmetics;
    private CompanionManager companionManager;

    public CompanionTimer(Plugin serverCosmetics, CompanionManager companionManager) {
        super(serverCosmetics);
        this.serverCosmetics = serverCosmetics;
        this.companionManager = companionManager;
    }

    @Override
    public BukkitRunnable getRunnable() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                List<Companion> companions = new ArrayList<>(companionManager.getCompanions().values());
                for (Companion companion : companions) {
                    companion.update();
                }
            }
        };
    }
}
