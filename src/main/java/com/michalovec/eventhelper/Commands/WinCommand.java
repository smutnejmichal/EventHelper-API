package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.EventHelper;
import com.michalovec.eventhelper.Managers.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WinCommand implements CommandExecutor {

    private final EventHelper plugin;

    public WinCommand(EventHelper plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        StatsManager statsManager = plugin.getStatsManager();

        final String prefix = "§x§E§6§F§1§1§B§lW§x§E§9§F§2§3§D§li§x§E§C§F§4§5§F§ln§x§E§F§F§5§8§1§ls §8• §7";

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + "§cPouze hráči mohou vidět své výhry.");
                return true;
            }

            Player player = (Player) sender;
            int wins = statsManager.getWins(player.getName());
            player.sendMessage(prefix + "Tvoje výhry: §x§E§6§F§1§1§B" + wins);
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
            if (!sender.hasPermission("eventhelper.addwins")) {
                sender.sendMessage(prefix + "§cNemáš oprávnění přidávat výhry.");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(prefix + "Hráč §x§E§6§F§1§1§B" + args[1] + " §7není online.");
                return true;
            }

            statsManager.addWin(target.getName());
            sender.sendMessage(prefix + "Přidána výhra hráči §x§E§6§F§1§1§B" + target.getName() + "§7.");
            target.sendMessage(prefix + "Získal jsi novou výhru!");

            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {
            if (!sender.hasPermission("eventwins.clear")) {
                sender.sendMessage(prefix + "§cNemáš oprávnění smazat všechny výhry.");
                return true;
            }

            statsManager.clearAll();
            sender.sendMessage(prefix + "Všechny výhry byly úspěšně smazány.");
            return true;
        }

        sender.sendMessage(prefix + "§cŠpatné použití. Použij: /win");
        return true;
    }
}
