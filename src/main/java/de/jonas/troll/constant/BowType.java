package de.jonas.troll.constant;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum BowType {

    EXPLOSION_BOW(
        "Explosions-Bogen",
        Enchantment.ARROW_FIRE,
        100,
        "explosionBow"
    ),
    DAMAGE_BOW(
        "Schaden-Bogen",
        Enchantment.ARROW_DAMAGE,
        100,
        "damageBow"
    ),
    KNOCKBACK_BOW(
        "Rückstoß-Bogen",
        Enchantment.ARROW_KNOCKBACK,
        100,
        "knockbackBow"
    );

    @Getter
    private final ItemStack bow;

    @Getter
    private final String metaData;

    BowType(
        @NotNull final String displayName,
        @NotNull final Enchantment enchantment,
        @Range(from = 0, to = Integer.MAX_VALUE) final int level,
        @NotNull final String metaData
    ) {
        ItemStack stack = new ItemStack(Material.BOW);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + displayName);
        meta.addEnchant(enchantment, level, true);
        stack.setItemMeta(meta);
        this.bow = stack;
        this.metaData = metaData;
    }

}
