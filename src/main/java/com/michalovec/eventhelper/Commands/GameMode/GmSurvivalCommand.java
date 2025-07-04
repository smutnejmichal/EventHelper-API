package com.michalovec.eventhelper.Commands.GameMode;

import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmSurvivalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player player) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(MessageManager.getWithWarp("messages.gamemodeSet", "SURVIVAL"));
            } else sender.sendMessage(MessageManager.get("messages.onlyPlayer"));
        } else if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(MessageManager.getWithWarp("messages.playerNotOnline", args[0]));
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            target.setGameMode(GameMode.SURVIVAL);
            target.sendMessage(MessageManager.getWithWarp("messages.gamemodeSet", "SURVIVAL"));
        }
        return true;
    }
}
