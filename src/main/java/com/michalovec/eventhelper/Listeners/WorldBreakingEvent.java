package com.michalovec.eventhelper.Listeners;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;

public class WorldBreakingEvent implements Listener {

    private Main main;

    private final HashMap<World, Boolean> worldAllowedBreaking = new HashMap<>();

    public WorldBreakingEvent(Main main){
        this.main = main;
    }


    @EventHandler
    public void onBreaking(BlockBreakEvent e){

        if (main.getRankManager().getRank(e.getPlayer().getUniqueId()) == Rank.ADMIN) return;

        if (!worldAllowedBreaking.containsKey(e.getBlock().getWorld())){
            worldAllowedBreaking.put(e.getBlock().getWorld(), true);
        }

        if (!worldAllowedBreaking.get(e.getBlock().getWorld())){
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Na tomto světě je zakázáno ničit blocky");
        }
    }


    public HashMap<World, Boolean> getWorldAllowedBreaking() {
        return worldAllowedBreaking;
    }
}
