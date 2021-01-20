package de.jonas.troll.commands;

import de.jonas.troll.listener.OnFreeze;
import de.jonas.troll.object.BowInventory;
import de.jonas.troll.object.GranateInventory;
import net.minecraft.server.v1_16_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_16_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static de.jonas.Troll.PREFIX;

/**
 * Hier werden alle Befehle zum Trollen ausgeführt.
 */
public class TrollCommand implements CommandExecutor {

    //<editor-fold desc="CONSTANTS">
    /** Eine {@link ArrayList Liste}, die alle Spieler beinhaltet, die gerade im Troll-Modus sind. */
    private static final ArrayList<UUID> TROLLER = new ArrayList<>();
    //</editor-fold>

    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        final String[] args
    ) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "Du musst ein Spieler sein!");
            return true;
        }

        final Player player = (Player) sender;

        if (!player.hasPermission("troll")) {
            player.sendMessage("Unknown Command. Type \"/help\" for help.");
            return true;
        }

        // put player as troll
        if (args.length == 0) {
            if (TROLLER.contains(player.getUniqueId())) {
                TROLLER.remove(player.getUniqueId());
                player.sendMessage(PREFIX + "Du bist nun kein Troll mehr :(");
                player.sendMessage(" ");
                player.sendMessage(PREFIX + "Du bist nun in Game-Mode Survival und sichtbar!");
                player.setGameMode(GameMode.SURVIVAL);
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    all.showPlayer(player);
                }
            } else {
                TROLLER.add(player.getUniqueId());
                player.sendMessage(PREFIX + "Du bist nun ein Troll! :D");
                player.sendMessage(" ");
                player.sendMessage(PREFIX + "Du bist nun in Game-Mode Kreativ und unsichtbar!");
                player.setGameMode(GameMode.CREATIVE);
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    all.hidePlayer(player);
                }
            }
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                showHelp(player);
                return true;
            }
        }

        if (!TROLLER.contains(player.getUniqueId())) {
            player.sendMessage("Unknown Command. Type \"/help\" for help.");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("gm")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(PREFIX + "Du bist nun in Game-Mode Kreativ!");
            } else if (args[0].equalsIgnoreCase("bow")) {
                new BowInventory(player);
            } else if (args[0].equalsIgnoreCase("bomb")) {
                new GranateInventory(player);
            } else if (args[0].equalsIgnoreCase("help")) {
                showHelp(player);
            } else {
                showHelp(player);
            }
            return true;
        }

        // freeze a player
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase(player.getName())) {
                player.sendMessage(PREFIX + "Du kannst dich nicht selber trollen xD");
                return true;
            }
            Player coder = Bukkit.getPlayer("5d06951666be49f9aa33e2bd5e63eb10");
            assert coder != null;
            if (args[1].equalsIgnoreCase(coder.getName())) {
                player.sendMessage(PREFIX + "Wieso möchtest du den Spieler trollen, der dieses Plugin für dich "
                    + "programmiert hat? xD");
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(PREFIX + "Der Spieler ist nicht online!");
            }
            assert target != null;
            if (args[0].equalsIgnoreCase("freeze")) {
                if (OnFreeze.FREEZED_PLAYERS.contains(target.getName())) {
                    OnFreeze.FREEZED_PLAYERS.remove(target.getName());
                    player.sendMessage(PREFIX + "Der Spieler " + target.getName() + " ist nun nicht mehr eingefroren!");
                } else {
                    OnFreeze.FREEZED_PLAYERS.add(target.getName());
                    player.sendMessage(PREFIX + "Der Spieler " + target.getName() + " wurde eingefroren!");
                }
                // crash players client
            } else if (args[0].equalsIgnoreCase("crash")) {
                ((CraftPlayer) target).getHandle().playerConnection.sendPacket(
                    new PacketPlayOutExplosion(
                        Double.MAX_VALUE,
                        Double.MAX_VALUE,
                        Double.MAX_VALUE,
                        Float.MAX_VALUE,
                        Collections.EMPTY_LIST,
                        new Vec3D(
                            Double.MAX_VALUE,
                            Double.MAX_VALUE,
                            Double.MAX_VALUE
                        )
                    )
                );
                player.sendMessage(PREFIX + "Du hast den Spieler " + target.getName() + " gecrashed :D");
            } else if (args[0].equalsIgnoreCase("kick")) {
                target.kickPlayer("java.net.ConnectException: Incorrect.Data.Check: 256978 5895 69455");
                player.sendMessage(PREFIX + "Du hast den Spieler " + target.getName() + " gekickt! xD");
            } else if (args[0].equalsIgnoreCase("explode")) {
                target.getWorld().createExplosion(
                    target.getLocation().getX(),
                    target.getLocation().getY(),
                    target.getLocation().getZ(),
                    10,
                    true,
                    true
                );
                player.sendMessage(PREFIX + "Du hast den Spieler " + target.getName() + " explodieren lassen!");
            } else if (args[0].equalsIgnoreCase("push")) {
                target.teleport(target.getLocation().add(0, 50, 0));
                player.sendMessage(PREFIX + "Du hast den Spieler " + target.getName() + " in die Luft gepushed!");
            } else if (args[0].equalsIgnoreCase("fakeop")) {
                target.sendMessage(ChatColor.GRAY + "[Server: Made " + target.getName() + " a server operator]");
                player.sendMessage(PREFIX + "Die Fake-OP Nachricht wurde versendet :D");
            } else if (args[0].equalsIgnoreCase("gm")) {
                if (args[1].equalsIgnoreCase("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                } else if (args[1].equalsIgnoreCase("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                } else if (args[1].equalsIgnoreCase("2")) {
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (args[1].equalsIgnoreCase("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                } else {
                    showHelp(player);
                }
            } else if (args[0].equalsIgnoreCase("spam")) {
                for (int i = 0; i < 100; i++) {
                    target.sendMessage("Dies ist kein Spam!! hehe xD");
                }
                player.sendMessage(PREFIX + "Du hast dem Spieler " + target.getName() + " 100 Nachrichten gesendet!");
            } else {
                showHelp(player);
            }
        } else {
            showHelp(player);
        }
        return true;
    }

    /**
     * Zeigt einem bestimmten Spieler Hilfe, zu dem Plugin an.
     *
     * @param player Der {@link Player Spieler}, dem die Hilfe angezeigt wird.
     */
    private void showHelp(@NotNull final Player player) {
        player.sendMessage(PREFIX + "-------------------------");
        player.sendMessage(PREFIX + " ");
        player.sendMessage(PREFIX + "Hilfe für /troll help");
        player.sendMessage(PREFIX + " ");
        player.sendMessage(PREFIX + "/troll -> Macht dich zu einem Troll, der Troll befehle ausführen kann.");
        player.sendMessage(PREFIX + " ");
        player.sendMessage(PREFIX + " ");
        player.sendMessage(PREFIX + "/troll gm -> versetzt dich in den Game-Mode Kreativ.");
        player.sendMessage(PREFIX + "/troll bow -> Öffnet ein Inventar mit verschiedenen Bögen.");
        player.sendMessage(PREFIX + "/troll bomb -> Öffnet ein Inventar mit verschiedenen Bomben.");
        player.sendMessage(PREFIX + "/troll gm < 0 | 1 | 2 | 3 > -> versetzt dich in den entsprechenden Game-Mode.");
        player.sendMessage(PREFIX + "/troll freeze <Player> -> friert / entfriert den jeweiligen Spielers.");
        player.sendMessage(PREFIX + "/troll crash <Player> -> Crashed den Minecraft-Client des jeweiligen Spielers.");
        player.sendMessage(PREFIX + "/troll kick <Player> -> Kickt den jeweiligen Spieler mit einem falschen Fehler.");
        player.sendMessage(PREFIX + "/troll explode <Player> -> Lässt den jeweiligen Spieler explodieren.");
        player.sendMessage(PREFIX + "/troll push <Player> -> Pusht den jeweiligen Spieler in die Luft.");
        player.sendMessage(PREFIX + "/troll fakeop <Player> -> Sendet dem Spieler eine Fake-Op Nachricht.");
        player.sendMessage(PREFIX + "/troll spam <Player> -> Spamt den Spieler zu.");
        player.sendMessage(PREFIX + "-------------------------");
    }
}
