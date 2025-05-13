package com.michalovec.eventhelper.commands;

import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class EventSettingsCommand implements CommandExecutor {

    private final Main plugin;

    public EventSettingsCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "Event Helper");

        ItemStack toggleItem = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = toggleItem.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Toggle Chat");
        meta.setLore(List.of(
                " ",
                ChatColor.GRAY + "Stav chatu: " + (plugin.isChatEnabled() ? ChatColor.GREEN + "Zapnutý" : ChatColor.RED + "Vypnutý"),
                ChatColor.YELLOW + "Klikni pro změnu"
        ));
        toggleItem.setItemMeta(meta);

        gui.setItem(4, toggleItem);

        player.openInventory(gui);
        return true;
    }

}
