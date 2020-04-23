package palight.serverCosmetics.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import palight.serverCosmetics.cosmetics.companion.Companion;
import palight.serverCosmetics.cosmetics.companion.CompanionManager;

public class CompanionCommand implements CommandExecutor {
    private Plugin serverCosmetics;
    private CompanionManager companionManager;

    public CompanionCommand(Plugin serverCosmetics, CompanionManager manager) {
        this.serverCosmetics = serverCosmetics;
        companionManager = manager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Companion playerCompanion = new Companion(serverCosmetics, "TEST COMPANION", new ItemStack(Material.STONE, 1), (Player) commandSender);
        companionManager.addCompanion(playerCompanion);
        return true;
    }
}
