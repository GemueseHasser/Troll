package de.jonas.troll.object;

import de.jonas.troll.constant.BowType;
import de.jonas.troll.constant.GranateType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Öffnet einem bestimmten {@link Player Spieler} ein Inventar, das alle Troll-Granaten beinhaltet.
 */
public class GranateInventory {

    /**
     * Erstellt eine neue und vollständig unabhängige Instanz des {@link GranateInventory Inventars} und öffnet es einem
     * bestimmten {@link Player Spieler}.
     *
     * @param player Der Spieler, dem das Inventar geöffnet wird.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public GranateInventory(@NotNull final Player player) {
        Inventory inventory = Bukkit.createInventory(
            null,
            45,
            ChatColor.GOLD + "" + ChatColor.BOLD + "Bomben"
        );
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        for (int i = 0; i < 36; i += 9) {
            inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        for (int i = 8; i < 45; i += 9) {
            inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        for (int i = 36; i < 45; i++) {
            inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        inventory.setItem(19, GranateType.LOW.getGranate());
        inventory.setItem(21, GranateType.MEDIUM.getGranate());
        inventory.setItem(23, GranateType.HIGH.getGranate());
        inventory.setItem(25, GranateType.EXTREME.getGranate());
        player.openInventory(inventory);
        BowInventory.CURRENT_INVENTORY_USER.add(player.getUniqueId());
    }

}
