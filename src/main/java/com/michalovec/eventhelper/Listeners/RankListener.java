package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.io.IOException;

public class RankListener implements Listener {

    private Main main;

    public RankListener(Main main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();



        if (!main.getRankManager().getConfig().contains(player.getUniqueId().toString())){
            YamlConfiguration config = main.getRankManager().getConfig();
            config.set(player.getUniqueId().toString(), Rank.HRAC.name());
            try {
                config.save(main.getRankManager().getFile());
            } catch (IOException x) {
                x.printStackTrace();
            }

        }

        PermissionAttachment attachment;
        if (main.getRankManager().getPerms().containsKey(player.getUniqueId())){
            attachment = main.getRankManager().getPerms().get(player.getUniqueId());
        }else {
            attachment = player.addAttachment(main);
            main.getRankManager().getPerms().put(player.getUniqueId(), attachment);
        }

        for (String perm : main.getRankManager().getRank(player.getUniqueId()).getPermissions()){
            attachment.setPermission(perm, true);
        }

        main.getNametagManager().setNametags(player);
        main.getNametagManager().newTag(player);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        main.getNametagManager().removeTag(e.getPlayer());
    }

}
