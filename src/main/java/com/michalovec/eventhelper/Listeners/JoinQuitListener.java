package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.EventHelper;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinQuitListener implements Listener {

    private EventHelper plugin;

    public JoinQuitListener (EventHelper plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%") + ChatColor.RESET + " ";
        event.setJoinMessage("§2§l+§r " + prefix + player.getDisplayName() + " §7(" + player.getServer().getOnlinePlayers().size() + " / " + player.getServer().getMaxPlayers() + ")");

        if (!plugin.isAnyGameRunning()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1,255, true, false));
            player.teleport(new Location(Bukkit.getWorld("Spawn"),0.5f,65.5f,0.5f,125,0));
            if (player.hasPermission("eventhelper.admin")) {
                player.setGameMode(GameMode.CREATIVE);
            } else {
                player.setGameMode(GameMode.ADVENTURE);
                player.getInventory().clear();
                player.setExp(0.0f);
                player.setLevel(0);
            }
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
