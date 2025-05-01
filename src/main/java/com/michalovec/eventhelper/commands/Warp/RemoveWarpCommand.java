package com.michalovec.eventhelper.commands.Warp;

import com.michalovec.eventhelper.Main;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class RemoveWarpCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageManager.get("messages.onlyPlayer"));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(MessageManager.get("messages.usageRemove"));
            return true;
        }

        File warpFile = new File(Main.getInstance().getDataFolder(), "warps/" + args[0] + ".yml");

        String name = args[0];

        if (!warpFile.exists()) {
            player.sendMessage(MessageManager.getWithWarp("messages.warpNotFound", name));
            return true;
        }

        if (warpFile.delete()) {
            player.sendMessage(MessageManager.getWithWarp("messages.warpDeleted", name));
        } else {
            player.sendMessage(MessageManager.getWithWarp("messages.warpDeleteFail", name));
        }

        return true;
    }
}
