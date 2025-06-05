package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.EventHelper;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.List;

public class WorldBreakingEvent implements Listener {

    private EventHelper main;

    public WorldBreakingEvent(EventHelper main){
        this.main = main;
    }

    @EventHandler
    public void onBreaking(BlockBreakEvent e){

        HashMap<World, Boolean> manager = main.getWorldBreakingManager().getWorldAllowedBreaking();
        HashMap<World, List<String>> materials = main.getWorldBreakingManager().getBypassBreaking();

        if (e.getPlayer().hasPermission("eventhelper.admin")) return;

        if (!manager.containsKey(e.getBlock().getWorld())){
            manager.put(e.getBlock().getWorld(), true);
        }

        if (!manager.get(e.getBlock().getWorld())){

            if (materials.get(e.getBlock().getWorld()).contains(e.getBlock().getType().name())){
                e.setCancelled(false);
                return;
            }

            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Na tomto světě je zakázáno ničit blocky");
        }
    }
}
