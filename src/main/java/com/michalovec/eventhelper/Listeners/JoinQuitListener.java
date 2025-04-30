package com.michalovec.eventhelper.Listeners;

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
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int left = player.getServer().getOnlinePlayers().size() - 1;

        event.setQuitMessage("§4§l-§r " + player.getDisplayName() + " §7(" + left + " / " + player.getServer().getMaxPlayers() + ")");
    }

}
