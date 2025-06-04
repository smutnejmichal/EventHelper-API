package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.EventHelper;
import com.michalovec.eventhelper.Managers.MessageManager;
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

    private final EventHelper plugin;

    public EventSettingsCommand(EventHelper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (!sender.isOp()) {
            player.sendMessage(MessageManager.get("messages.playerIsNotOP"));
            return false;
        }

        Inventory gui = Bukkit.createInventory(null, 9, "§x§E§4§3§A§9§6§lE§x§D§D§3§A§9§A§lv§x§D§6§3§A§9§E§le§x§C§F§3§A§A§3§ln§x§C§8§3§A§A§7§lt §x§B§A§3§A§A§F§lS§x§B§3§3§9§B§4§le§x§A§C§3§9§B§8§lt§x§A§5§3§9§B§C§lt§x§9§E§3§9§C§0§li§x§9§7§3§9§C§5§ln§x§9§0§3§9§C§9§lg§x§8§9§3§9§C§D§ls");

        ItemStack toggleItem = new ItemStack(Material.FEATHER);
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
