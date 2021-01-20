package de.jonas.troll.listener;

import de.jonas.Troll;
import de.jonas.troll.constant.GranateType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class ShootBomb implements Listener {

    @EventHandler
    public void shootBomb(@NotNull final PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        for (final GranateType type : GranateType.values()) {
            if (e.getItem().equals(type.getGranate())) {
                final Item bomb = e.getPlayer().getWorld().dropItem(e.getPlayer().getEyeLocation(), type.getGranate());
                bomb.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(2.5D));
                BukkitTask task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        bomb.getWorld().createExplosion(bomb.getLocation(), type.getRange(), true, true);
                    }
                }.runTaskLater(Troll.getInstance(), 25);
                return;
            }
        }
    }

}
