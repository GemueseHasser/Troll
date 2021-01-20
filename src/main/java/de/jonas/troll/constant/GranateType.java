package de.jonas.troll.constant;

import de.jonas.Troll;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum GranateType {

    LOW(
        ChatColor.GREEN,
        "Low",
        10
    ),
    MEDIUM(
        ChatColor.GOLD,
        "Medium",
        20
    ),
    HIGH(
        ChatColor.RED,
        "High",
        50
    ),
    EXTREME(
        ChatColor.DARK_RED,
        "Extreme",
        100
    );

    @Getter
    private final ItemStack granate;

    @Getter
    private final int range;

    GranateType(
        @NotNull final ChatColor color,
        @NotNull final String name,
        @Range(from = 0, to = Integer.MAX_VALUE) final int range
    ) {
        ItemStack stack = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(color + "" + ChatColor.BOLD + name);
        stack.setItemMeta(meta);
        this.granate = stack;
        this.range = range;
    }

}
