package de.jonas.troll.listener;

import de.jonas.troll.constant.BowType;
import de.jonas.troll.object.BowInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Das anklicken, des Bogen-Inventars wird gesteuert.
 */
public class OnInventoryClick implements Listener {

    @EventHandler
    public void onClick(@NotNull final InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (!BowInventory.CURRENT_INVENTORY_USER.contains(player.getUniqueId())) {
            return;
        }
        if (e.getCurrentItem() == null) return;
        for (final BowType type : BowType.values()) {
            if (Objects.equals(e.getCurrentItem(), type.getBow())) {
                return;
            }
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(@NotNull final InventoryCloseEvent e) {
        BowInventory.CURRENT_INVENTORY_USER.remove(e.getPlayer().getUniqueId());
    }

}
