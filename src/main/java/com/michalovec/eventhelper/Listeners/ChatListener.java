package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.EventHelper;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final EventHelper plugin;

    public ChatListener(EventHelper plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat (AsyncPlayerChatEvent e){
        e.setCancelled(true);
        Player player = e.getPlayer();
        if (!plugin.isChatEnabled() && (!player.hasPermission("eventhelper.admin") || player.hasPermission("eventhelper.chat.bypass"))) {
            player.sendMessage("§cChat je vypnutý!");
            return;
        }

        String prefix;
        if (plugin.getTestManager().isGameRunning()) {
            prefix = plugin.getRankManager().getRank(player.getUniqueId()).getDisplay();
        } else {
            prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
        }

        Bukkit.broadcastMessage(prefix + " " + ChatColor.WHITE + player.getName() + "§8" + " → " + ChatColor.WHITE + e.getMessage());
    }
}
