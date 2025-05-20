package com.michalovec.eventhelper.Commands.Warp;

import com.michalovec.eventhelper.Managers.MessageManager;
import com.michalovec.eventhelper.Managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CreateWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageManager.get("messages.onlyPlayer"));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(MessageManager.get("messages.usageCreate"));
            return true;
        }

        String name = args[0];

        if (WarpManager.warpExists(name)) {
            player.sendMessage(MessageManager.getWithWarp("messages.warpExists", name));
            return true;
        }

        try {
            WarpManager.createWarp(name, player.getLocation());
            player.sendMessage(MessageManager.getWithWarp("messages.warpCreated", name));
        } catch (IOException e) {
            player.sendMessage(MessageManager.get("messages.warpDeleteFail"));
            e.printStackTrace();
        }

        return true;
    }
}
