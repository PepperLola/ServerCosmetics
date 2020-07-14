package palight.serverCosmetics;

import org.bukkit.plugin.java.JavaPlugin;
import palight.serverCosmetics.commands.CompanionCommand;
import palight.serverCosmetics.cosmetics.companion.CompanionManager;
import palight.serverCosmetics.items.CosmeticItem;
import palight.serverCosmetics.items.movement.MagicStickItem;
import palight.serverCosmetics.listeners.PlayerJumpListener;
import palight.serverCosmetics.listeners.PlayerListener;
import palight.serverCosmetics.timers.CompanionTimer;
import palight.serverCosmetics.timers.TrampolineTimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerCosmetics extends JavaPlugin {

    private CompanionManager companionManager = new CompanionManager();
    private List<Timer> timers = new ArrayList<>();
    private List<CustomListener> listeners = new ArrayList<>();

    private List<CosmeticItem> cosmeticItems = new ArrayList<>();

    @Override
    public void onEnable() {
        super.onEnable();
        this.getCommand("companion").setExecutor(new CompanionCommand(this, companionManager));

        timers.add(new CompanionTimer(this, companionManager));
        timers.add(new TrampolineTimer(this));

        for (Timer timer : timers) {
            timer.startTimer(1);
        }

        cosmeticItems.addAll(Arrays.asList(
                new MagicStickItem(this)
        ));

        listeners.add(new PlayerJumpListener(this));
        listeners.add(new PlayerListener(this));

        for (CustomListener listener : listeners) {
            listener.enable();
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

    public CompanionManager getCompanionManager() {
        return companionManager;
    }

    public void setCompanionManager(CompanionManager companionManager) {
        this.companionManager = companionManager;
    }

    public List<CosmeticItem> getCosmeticItems() {
        return cosmeticItems;
    }

    public void setCosmeticItems(List<CosmeticItem> cosmeticItems) {
        this.cosmeticItems = cosmeticItems;
    }
}
