package de.jonas.troll.object;

import de.jonas.troll.constant.BowType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Öffnet einem bestimmten {@link Player Spieler} ein Inventar, das alle Troll-Bögen beinhaltet.
 */
public class BowInventory {

    //<editor-fold desc="CONSTANTS">
    /** Eine Liste, mit allen Nutzern, die gerade das Bogen-Inventar geöffnet haben. */
    public static final List<UUID> CURRENT_INVENTORY_USER = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="CONSTRUCTOR">
    /**
     * Erstellt eine neue und vollständig unabhängige Instanz des {@link BowInventory Inventars} und öffnet es einem
     * bestimmten {@link Player Spieler}.
     *
     * @param player Der Spieler, dem das Inventar geöffnet wird.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public BowInventory(@NotNull final Player player) {
        Inventory inventory = Bukkit.createInventory(
            null,
            45,
            ChatColor.GOLD + "" + ChatColor.BOLD + "Super-Bögen"
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
        inventory.setItem(21, BowType.DAMAGE_BOW.getBow());
        inventory.setItem(22, BowType.EXPLOSION_BOW.getBow());
        inventory.setItem(23, BowType.KNOCKBACK_BOW.getBow());
        player.openInventory(inventory);
        CURRENT_INVENTORY_USER.add(player.getUniqueId());
    }
    //</editor-fold>

}
