package palight.serverCosmetics;

import org.bukkit.plugin.java.JavaPlugin;
import palight.serverCosmetics.commands.CompanionCommand;
import palight.serverCosmetics.cosmetics.companion.Companion;
import palight.serverCosmetics.cosmetics.companion.CompanionManager;
import palight.serverCosmetics.timers.CompanionTimer;
import palight.serverCosmetics.timers.Timer;

import java.util.ArrayList;
import java.util.List;

public class ServerCosmetics extends JavaPlugin {

    CompanionManager companionManager = new CompanionManager();
    List<Timer> timers = new ArrayList<>();

    @Override
    public void onEnable() {
        super.onEnable();
        this.getCommand("companion").setExecutor(new CompanionCommand(this, companionManager));

        timers.add(new CompanionTimer(this, companionManager));

        for (Timer timer : timers) {
            timer.startTimer(1);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }
}
