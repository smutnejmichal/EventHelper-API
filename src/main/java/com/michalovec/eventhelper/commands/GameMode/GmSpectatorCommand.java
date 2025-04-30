package com.michalovec.eventhelper.commands.GameMode;

import com.michalovec.eventhelper.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmSpectatorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player player) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(MessageManager.getWithWarp("messages.gamemodeSet", "SPECTATOR"));
            } else sender.sendMessage(MessageManager.get("messages.onlyPlayer"));
        } else if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(MessageManager.getWithWarp("messages.playerNotOnline", args[0]));
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            target.setGameMode(GameMode.SPECTATOR);
            target.sendMessage(MessageManager.getWithWarp("messages.gamemodeSet", "SPECTATOR"));

        }
        return true;
    }
}
