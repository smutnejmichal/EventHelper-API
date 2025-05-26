package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Bukkit.getPlayer(args[0]).setFoodLevel(20);
                        player.sendMessage(MessageManager.getWithWarp("messages.playerFeed", args[0]));
                    }
                    else player.sendMessage(MessageManager.getWithWarp("messages.playerNotOnline", args[0]));
                } else if (args.length == 0) {
                    player.setFoodLevel(20);
                    player.sendMessage(MessageManager.getWithWarp("messages.playerFeed", player.getName()));
                }
                else player.sendMessage(MessageManager.get("messages.feedWrongUsage"));
            }
            else player.sendMessage(MessageManager.get("messages.playerIsNotOP"));
        }
        else sender.sendMessage(MessageManager.get("messages.onlyPlayer"));

        return true;
    }
}
