package com.michalovec.eventhelper.Commands.Warp;

import com.michalovec.eventhelper.Managers.MessageManager;
import com.michalovec.eventhelper.Managers.WarpManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageManager.get("messages.onlyPlayer"));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(MessageManager.get("messages.usageWarp"));
            return true;
        }

        String name = args[0];
        Location warpLoc = WarpManager.getWarp(name);

        if (warpLoc == null) {
            player.sendMessage(MessageManager.getWithWarp("messages.warpNotFound", name));
            return true;
        }

        player.teleport(warpLoc);
        player.sendMessage(MessageManager.getWithWarp("messages.teleportSuccess", name));
        return true;
    }
}
