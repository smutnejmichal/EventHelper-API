package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.Main;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EventSettingsGuiListener implements Listener {

    private final Main plugin;

    public EventSettingsGuiListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;
        if (e.getView().getTitle().equals("§x§E§4§3§A§9§6§lE§x§D§D§3§A§9§A§lv§x§D§6§3§A§9§E§le§x§C§F§3§A§A§3§ln§x§C§8§3§A§A§7§lt §x§B§A§3§A§A§F§lS§x§B§3§3§9§B§4§le§x§A§C§3§9§B§8§lt§x§A§5§3§9§B§C§lt§x§9§E§3§9§C§0§li§x§9§7§3§9§C§5§ln§x§9§0§3§9§C§9§lg§x§8§9§3§9§C§D§ls")) {
            e.setCancelled(true);

            ItemStack clicked = e.getCurrentItem();
            if (clicked == null || clicked.getType() != Material.FEATHER) return;

            plugin.toggleChatEnabled();
            Bukkit.broadcastMessage(MessageManager.get("messages.chatStatus") + (plugin.isChatEnabled() ? ChatColor.GREEN + "Zapnutý" : ChatColor.RED + "Vypnutý") + "§7.");

            Bukkit.getScheduler().runTask(plugin, () -> player.performCommand("eventsettings"));
        }
    }
}

