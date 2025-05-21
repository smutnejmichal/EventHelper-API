package com.michalovec.eventhelper.Commands.BlockedCommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class BlockCommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandSendEvent e){

        e.getCommands().removeIf(command -> command.contains("minecraft:say"));
        e.getCommands().removeIf(command -> command.contains("minecraft:me"));
        e.getCommands().removeIf(command -> command.contains("minecraft:tell"));

    }
}
