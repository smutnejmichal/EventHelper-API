package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import com.michalovec.eventhelper.Managers.WorldBreakingManager;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;

public class WorldBreakingEvent implements Listener {

    private Main main;

    public WorldBreakingEvent(Main main){
        this.main = main;
    }


    @EventHandler
    public void onBreaking(BlockBreakEvent e){

        HashMap<World, Boolean> manager = main.getWorldBreakingManager().getWorldAllowedBreaking();

        if (main.getRankManager().getRank(e.getPlayer().getUniqueId()) == Rank.ADMIN) return;

        if (!manager.containsKey(e.getBlock().getWorld())){
            manager.put(e.getBlock().getWorld(), true);
        }

        if (!manager.get(e.getBlock().getWorld())){
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Na tomto světě je zakázáno ničit blocky");
        }
    }
}
