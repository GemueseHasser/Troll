package de.jonas.troll.listener;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Das {@link Event}, welches für den Freeze genutzt wird.
 */
public class OnFreeze implements Listener {

    //<editor-fold desc="CONSTANTS">
    /** Eine {@link List Liste}, welche alle eingefrorenen Spieler beinhaltet. */
    public static final List<String> FREEZED_PLAYERS = new ArrayList<>();
    //</editor-fold>

    @EventHandler
    public void onFreeze(@NotNull final PlayerMoveEvent e) {
        if (!FREEZED_PLAYERS.contains(e.getPlayer().getName())) {
            return;
        }
        e.setCancelled(true);
    }

}
