package me.amelia.inventoryclear.GUIs;

import me.amelia.inventoryclear.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ConfirmGUI implements Listener {
    private final Main plugin;

    private final Inventory inventory;

    public ConfirmGUI(Main plugin) {
        this.plugin = plugin;

        this.inventory = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&',"&cConfirm Inventory Clear"));

        this.initializeItems();

        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public void initializeItems() {
        this.inventory.setItem(3, createGuiItem(Material.REDSTONE, ChatColor.translateAlternateColorCodes('&',"&4Confirm"), ChatColor.translateAlternateColorCodes('&',"&4&lThis will delete ALL items within your inventory.")));
        this.inventory.setItem(5, createGuiItem(Material.EMERALD, ChatColor.translateAlternateColorCodes('&',"&aCancel"), ""));

        for (int i = 0; i < this.inventory.getSize(); i++) {
            if (this.inventory.getItem(i) == null) this.inventory.setItem(i, new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1, (short) 6));
        }
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);

        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(Player player) {
        player.openInventory(this.inventory);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!event.getInventory().equals(this.inventory)) return;

        event.setCancelled(true);

        final ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getRawSlot() == 3) {
            player.closeInventory();
            player.getInventory().clear();

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.Cleared").replace("{PLAYER}", player.getDisplayName())));
        }

        if (event.getRawSlot() == 5) {
            player.closeInventory();
        }
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent event) {
        if (event.getInventory().equals(inventory)) event.setCancelled(true);
    }
}
