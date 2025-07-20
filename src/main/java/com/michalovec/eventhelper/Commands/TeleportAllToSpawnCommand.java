package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportAllToSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(MessageManager.get("messages.onlyPlayer"));
            return true;
        }

        if (!player.hasPermission("eventhelper.admin")) {
            player.sendMessage(MessageManager.get("messages.playerIsNotOP"));
            return true;
        }

        Location loc = new Location(Bukkit.getWorld("spawn"), 0.5f, 65, 0.5f, 125, 0);
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.teleport(loc);

            if (target.hasPermission("eventhelper.admin")) continue;

            target.getInventory().clear();
            target.setExp(0f);
            target.setLevel(0);
        }

        return true;
    }
}
