package com.michalovec.eventhelper.commands;

import com.michalovec.eventhelper.Main;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventHelperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(" ");
            sender.sendMessage(MessageManager.get("messages.helpHeader"));
            sender.sendMessage(MessageManager.get("messages.helpReload"));
            sender.sendMessage(MessageManager.get("messages.helpVersion"));
            sender.sendMessage(MessageManager.get("messages.helpHelp"));
            sender.sendMessage(" ");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            MessageManager.reload();
            sender.sendMessage(MessageManager.get("messages.configReloaded"));
            return true;
        }

        if (args[0].equalsIgnoreCase("version")) {
            String version = Main.getInstance().getDescription().getVersion();
            sender.sendMessage(MessageManager.get("messages.versionMessage").replace("%version%", version));
            return true;
        }

        sender.sendMessage(MessageManager.get("messages.unknownSubcommand"));
        return true;
    }
}
