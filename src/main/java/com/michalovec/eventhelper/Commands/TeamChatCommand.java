package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class TeamChatCommand implements CommandExecutor {

    private final Main main;
    private final Set<Player> spies = new HashSet<>();

    public TeamChatCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageManager.get("messages.onlyPlayer"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equalsIgnoreCase("spy")) {
            if (getRank(player) == Rank.ADMIN || player.isOp()) {
                if (spies.contains(player)) {
                    spies.remove(player);
                    player.sendMessage(MessageManager.get("messages.chatSpyModeDisabled"));
                } else {
                    spies.add(player);
                    player.sendMessage(MessageManager.get("messages.chatSpyModeEnabled"));
                }
            } else {
                player.sendMessage(MessageManager.get("messages.playerIsNotOP"));
            }
            return true;
        }

        if (args.length == 0 && (getRank(player) == Rank.ADMIN || player.isOp())) {
            player.sendMessage(MessageManager.get("messages.chatWrongUsageOP"));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(MessageManager.get("messages.chatWrongUsage"));
            return true;
        }

        Rank playerRank = getRank(player);
        String message = String.join(" ", args);
        String formattedMessage = "§8[§4Chat§8] " + ChatColor.WHITE + player.getName() + "§8 → " + ChatColor.WHITE + message;
        String spyMessage = "§8[§4Spy§8] §7(Tým: " + playerRank.name() + ") " + ChatColor.WHITE + player.getName() + "§8 → " + ChatColor.WHITE + message;

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (getRank(onlinePlayer) == playerRank && onlinePlayer != player) {
                onlinePlayer.sendMessage(formattedMessage);
            }
        }
        player.sendMessage(formattedMessage); // Poslat zprávu i odesílateli

        // Spy mód - admini vidí všechny zprávy s označením týmu
        for (Player spy : spies) {
            if (spy != player && playerRank != Rank.ADMIN) {
                spy.sendMessage(ChatColor.GRAY + spyMessage);
            }
        }

        return true;
    }

    private Rank getRank(Player player) {
        return main.getRankManager().getRank(player.getUniqueId());
    }
}
