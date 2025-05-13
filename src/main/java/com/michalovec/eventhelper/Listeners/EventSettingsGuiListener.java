package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.Main;
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
        if (e.getView().getTitle().equals(ChatColor.DARK_GREEN + "Event Helper")) {
            e.setCancelled(true);

            ItemStack clicked = e.getCurrentItem();
            if (clicked == null || clicked.getType() != Material.EMERALD_BLOCK) return;

            plugin.toggleChatEnabled();

            Bukkit.getScheduler().runTask(plugin, () -> player.performCommand("eventsettings"));
        }
    }
}

