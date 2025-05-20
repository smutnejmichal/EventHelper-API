package com.michalovec.eventhelper.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        int maxPlayers = Bukkit.getMaxPlayers();

        StringBuilder builder = new StringBuilder();
        builder.append(ChatColor.GOLD + "⏵ " + ChatColor.YELLOW + "Online hráči ")
                .append(ChatColor.GRAY + "(" + onlinePlayers.length + "/" + maxPlayers + "): ")
                .append(ChatColor.GREEN);

        for (int i = 0; i < onlinePlayers.length; i++) {
            builder.append(onlinePlayers[i].getName());
            if (i < onlinePlayers.length - 1) {
                builder.append(ChatColor.GRAY).append(", ").append(ChatColor.GREEN);
            }
        }

        sender.sendMessage(builder.toString());
        return true;
    }
}
