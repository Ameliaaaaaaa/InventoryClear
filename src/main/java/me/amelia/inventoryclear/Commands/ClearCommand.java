package me.amelia.inventoryclear.Commands;

import me.amelia.inventoryclear.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {
    private final Main plugin;

    public ClearCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String permission = this.plugin.getConfig().getString("Permission");

        if (sender instanceof Player) {
            if (sender.hasPermission(permission)) {
                Player player = (Player) sender;

                this.plugin.getConfirmGUI().openInventory(player);
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.NoPermission").replace("{PERMISSION}", permission)));
            }
        }

        return true;
    }
}
