package com.michalovec.eventhelper.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("§2§l+§r " + player.getDisplayName() + " §7(" + player.getServer().getOnlinePlayers().size() + " / " + player.getServer().getMaxPlayers() + ")");

        player.setPlayerListHeaderFooter("\n\n\uE085\n", "\n§fPing: §x§F§F§5§4§5§40ms§f • Hráči: §x§F§F§5§4§5§4" + Bukkit.getOnlinePlayers().size() + "\n");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int left = player.getServer().getOnlinePlayers().size() - 1;

        event.setQuitMessage("§4§l-§r " + player.getDisplayName() + " §7(" + left + " / " + player.getServer().getMaxPlayers() + ")");
    }

}
