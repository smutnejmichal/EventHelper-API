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

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Pouze hráči mohou vidět své výhry.");
                return true;
            }

            Player player = (Player) sender;
            int wins = statsManager.getWins(player.getName());
            player.sendMessage(ChatColor.GREEN + "Tvoje výhry: " + ChatColor.GOLD + wins);
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
            if (!sender.hasPermission("eventhelper.addwins")) {
                sender.sendMessage(ChatColor.RED + "Nemáš oprávnění přidávat výhry.");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Hráč '" + args[1] + "' není online.");
                return true;
            }

            statsManager.addWin(target.getName());
            sender.sendMessage(ChatColor.GREEN + "Přidána výhra hráči " + ChatColor.YELLOW + target.getName());
            target.sendMessage(ChatColor.GOLD + "Získal jsi novou výhru!");

            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {
            if (!sender.hasPermission("eventwins.clear")) {
                sender.sendMessage(ChatColor.RED + "Nemáš oprávnění smazat všechny výhry.");
                return true;
            }

            statsManager.clearAll();
            sender.sendMessage(ChatColor.GREEN + "Všechny výhry byly úspěšně smazány.");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Použití:");
        sender.sendMessage(ChatColor.YELLOW + "/" + label + ChatColor.GRAY + " - zobrazí tvoje výhry");
        sender.sendMessage(ChatColor.YELLOW + "/" + label + " add <hráč>" + ChatColor.GRAY + " - přidá výhru hráči");
        return true;
    }
}
