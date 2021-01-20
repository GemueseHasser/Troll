package de.jonas.troll.listener;

import de.jonas.Troll;
import de.jonas.troll.constant.BowType;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OnShoot implements Listener {

    @EventHandler
    public void onProjectileLaunch(@NotNull final ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof Arrow && e.getEntity().getShooter() instanceof Player) {
            Player player = (Player) e.getEntity().getShooter();
            if (player.getInventory().getItemInMainHand().equals(BowType.EXPLOSION_BOW.getBow())) {
                e.getEntity().setMetadata(
                    BowType.EXPLOSION_BOW.getMetaData(),
                    new FixedMetadataValue(Troll.getInstance(), "yess!")
                );
            }
        }
    }

    @EventHandler
    @SuppressWarnings("checkstyle:MagicNumber")
    public void onProjectileHit(@NotNull final ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow
            && e.getEntity().hasMetadata(BowType.EXPLOSION_BOW.getMetaData())
        ) {
            Location loc = e.getEntity().getLocation();
            Objects.requireNonNull(loc.getWorld()).createExplosion(loc, 10, true, true);
        }
    }

}
