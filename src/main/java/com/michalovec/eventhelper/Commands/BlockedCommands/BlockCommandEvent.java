package com.michalovec.eventhelper.Commands.BlockedCommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class BlockCommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandSendEvent e){
        e.getCommands().remove("me");
    }

    @EventHandler
    public void onPlayerCommand(PlayerChatTabCompleteEvent e){

        e.getTabCompletions().removeIf(command -> command.contains("minecraft:me"));
        e.getTabCompletions().removeIf(command -> command.contains(":me"));
        e.getTabCompletions().removeIf(command -> command.contains("minecraft:say"));

    }

    @EventHandler
    public void onPlayerCommand(AsyncPlayerChatEvent e){

       if (e.equals(e.getMessage().contains("/minecraft:me"))){
           e.setCancelled(true);
       }
       if (e.equals(e.getMessage().contains("minecraft:me"))){
           e.setCancelled(true);
       }
       if (e.equals(e.getMessage().contains("me"))){
           e.setCancelled(true);
       }

    }
}
