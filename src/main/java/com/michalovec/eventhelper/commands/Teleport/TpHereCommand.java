package com.michalovec.eventhelper.commands.Teleport;

import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpHereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if ((sender instanceof Player player)) {
            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.teleport(player);
                }
                else player.sendMessage(MessageManager.getWithWarp("messages.playerNotOnline", args[0]));
            }
            else player.sendMessage(MessageManager.get("messages.usageTpHere"));
        }
        else sender.sendMessage(MessageManager.get("messages.onlyPlayer"));


        return true;
    }
}
