package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(MessageManager.get("messages.onlyPlayer"));
            return false;
        }

        if (!player.hasPermission("eventhelper.admin")) {
            player.sendMessage(MessageManager.get("messages.playerIsNotOP"));
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) {
            player.sendMessage(MessageManager.get("messages.hatNotAnItem"));
            return false;
        }

        player.getInventory().setHelmet(new ItemStack(item.getType(), 1));
        player.getInventory().removeItem(item);
        player.sendMessage(MessageManager.get("messages.hatSuccess"));

        return true;
    }
}
