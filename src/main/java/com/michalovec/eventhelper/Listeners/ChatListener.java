package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.Core.GameTeam;
import com.michalovec.eventhelper.Core.TeamAPI;
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
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if (!plugin.isChatEnabled() && !player.hasPermission("eventhelper.admin")) {
            player.sendMessage("§cChat je vypnutý!");
            e.setCancelled(true);
            return;
        }

        String prefix;
        GameTeam team = TeamAPI.getTeam(player);
        if (team != null) {
            prefix = team.getDisplayName();
        } else {
            prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
        }

        prefix = prefix.replace("%", "%%"); // jen pro formatovací část, ne pro message
        String name = player.getName();

        if (player.hasPermission("eventhelper.admin")) {
            e.setFormat(prefix + " §f" + name + "§8 » §x§0§3§E§2§A§E%2$s");
            return;
        }

        e.setFormat(prefix + " §f" + name + "§8 » §f%2$s");
    }
}
