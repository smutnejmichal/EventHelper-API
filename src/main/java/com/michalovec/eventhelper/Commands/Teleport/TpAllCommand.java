package com.michalovec.eventhelper.Commands.Teleport;

import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if ((sender instanceof Player player)) {
            for (Player target : Bukkit.getOnlinePlayers()) {
                target.teleport(player.getLocation());
            }
        }
        else sender.sendMessage(MessageManager.get("messages.onlyPlayer"));


        return true;
    }
}
