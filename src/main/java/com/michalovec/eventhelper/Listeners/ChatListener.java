package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final Main main;

    public ChatListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onChat (AsyncPlayerChatEvent e){
        e.setCancelled(true);
        Player player = e.getPlayer();
        if (!main.isChatEnabled() && main.getRankManager().getRank(player.getUniqueId()) != Rank.ADMIN) {
            player.sendMessage("§cChat je vypnutý!");
            return;
        }

        Bukkit.broadcastMessage(main.getRankManager().getRank(player.getUniqueId()).getDisplay() + " " + ChatColor.WHITE + player.getName() + "§8" + " → " + ChatColor.WHITE + e.getMessage());
    }
}
