package de.jonas;

import de.jonas.troll.commands.TrollCommand;
import de.jonas.troll.listener.OnFreeze;
import de.jonas.troll.listener.OnInventoryClick;
import de.jonas.troll.listener.OnShoot;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * Die Haupt- und Main-Klasse des Plugins. Hier läuft alle zusammen und es wird alles registriert, um das Plugin zu
 * starten.
 */
public class Troll extends JavaPlugin {


    //<editor-fold desc="CONSTANTS">
    /** Der Prefix, der vor allen Nachrichten steht, die von diesem Plugin gesendet werden. */
    public static final String PREFIX = ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED
        + "Troll" + ChatColor.WHITE + "" + ChatColor.BOLD + "] " + ChatColor.GOLD;
    /** Die Referenz, mit der auf die Konsole zugegriffen wird. */
    private static final ConsoleCommandSender CONSOLE = Bukkit.getConsoleSender();
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Die Instanz, mit der man auf das Plugin zugreifen kann. */
    @Getter
    private static Troll instance;
    //</editor-fold>

    //<editor-fold desc="setup and shutdown">
    @Override
    public void onEnable() {
        instance = this;
        CONSOLE.sendMessage(PREFIX + "Das Plugin wurde erfolgreich aktiviert! by Gemuese_Hasser / Jonas0206");

        Objects.requireNonNull(getCommand("troll")).setExecutor(new TrollCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new OnFreeze(), this);
        pm.registerEvents(new OnInventoryClick(), this);
        pm.registerEvents(new OnShoot(), this);
    }

    @Override
    public void onDisable() {
        CONSOLE.sendMessage(PREFIX + "Das Plugin wurde deaktiviert! by Gemuese_Hasser / Jonas0206");
    }
    //</editor-fold>
}
