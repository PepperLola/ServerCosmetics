package palight.serverCosmetics.timers;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Timer {
    private Plugin serverCosmetics;
    private BukkitRunnable runnable;

    public Timer(Plugin serverCosmetics) {
        this.serverCosmetics = serverCosmetics;
    }
    public void startTimer(long period) {
        runnable = getRunnable();
        runnable.runTaskTimer(serverCosmetics, 0, period);
    }
    public void stopTimer() {
        if (runnable != null && !runnable.isCancelled()) {
            runnable.cancel();
        }
    }
    public abstract BukkitRunnable getRunnable();
}
