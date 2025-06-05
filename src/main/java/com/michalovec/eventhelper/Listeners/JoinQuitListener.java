package com.michalovec.eventhelper.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%") + ChatColor.RESET + " ";
        event.setJoinMessage("§2§l+§r " + prefix + player.getDisplayName() + " §7(" + player.getServer().getOnlinePlayers().size() + " / " + player.getServer().getMaxPlayers() + ")");

        if (player.hasPermission("eventhelper.admin")) {
            player.setGameMode(GameMode.CREATIVE);
        } else {
            player.getInventory().clear();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%") + ChatColor.RESET + " ";
        int left = player.getServer().getOnlinePlayers().size() - 1;

        event.setQuitMessage("§4§l-§r " + prefix + player.getDisplayName() + " §7(" + left + " / " + player.getServer().getMaxPlayers() + ")");
    }

}
