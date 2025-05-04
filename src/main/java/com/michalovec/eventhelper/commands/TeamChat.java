package com.michalovec.eventhelper.commands;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class TeamChat implements CommandExecutor {

    private final Main main;
    private final Set<Player> spies = new HashSet<>();

    public TeamChat(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Tento příkaz mohou použít pouze hráči.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equalsIgnoreCase("spy")) {
            if (getRank(player) == Rank.ADMIN || player.isOp()) {
                if (spies.contains(player)) {
                    spies.remove(player);
                    player.sendMessage(ChatColor.RED + "Spy mód byl vypnut.");
                } else {
                    spies.add(player);
                    player.sendMessage(ChatColor.GREEN + "Spy mód byl zapnut.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Nemáš oprávnění použít tento příkaz.");
            }
            return true;
        }

        if (args.length == 0 && (getRank(player) == Rank.ADMIN || player.isOp())) {
            player.sendMessage(ChatColor.RED + "Použití: /chat <zpráva> nebo /chat spy");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Použití: /chat <zpráva> nebo /chat spy");
            return true;
        }

        Rank playerRank = getRank(player);
        String message = String.join(" ", args);
        String formattedMessage = "§8[§6Týmová zpráva§8] " + ChatColor.WHITE + player.getName() + "§8 → " + ChatColor.WHITE + message;
        String spyMessage = "§8[§6Spy§8] §7(Tým: " + playerRank.name() + ") " + ChatColor.WHITE + player.getName() + "§8 → " + ChatColor.WHITE + message;

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (getRank(onlinePlayer) == playerRank && onlinePlayer != player) {
                onlinePlayer.sendMessage(formattedMessage);
            }
        }
        player.sendMessage(formattedMessage); // Poslat zprávu i odesílateli

        // Spy mód - admini vidí všechny zprávy s označením týmu
        for (Player spy : spies) {
            if (spy != player) {
                spy.sendMessage(ChatColor.GRAY + spyMessage);
            }
        }

        return true;
    }

    private Rank getRank(Player player) {
        return main.getRankManager().getRank(player.getUniqueId());
    }
}
