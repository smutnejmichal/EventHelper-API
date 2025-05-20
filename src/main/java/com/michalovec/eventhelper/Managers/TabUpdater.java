package com.michalovec.eventhelper.Managers;

import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TabUpdater extends BukkitRunnable {

    private final Main plugin;

    public TabUpdater(Main plugin) {
        this.plugin = plugin;
    }

    public void start() {
        this.runTaskTimer(plugin, 0L, 200L);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int ping = player.getPing();
            player.setPlayerListFooter("\n§fPing: §x§F§F§5§4§5§4" + ping + "ms§f • Hráči: §x§F§F§5§4§5§4" + Bukkit.getOnlinePlayers().size() + "\n");
        }
    }

}
