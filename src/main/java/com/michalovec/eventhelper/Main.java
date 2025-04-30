package com.michalovec.eventhelper;

import com.michalovec.eventhelper.commands.*;
import com.michalovec.eventhelper.commands.GameMode.GmAdventureCommand;
import com.michalovec.eventhelper.commands.GameMode.GmCreativeCommand;
import com.michalovec.eventhelper.commands.GameMode.GmSpectatorCommand;
import com.michalovec.eventhelper.commands.GameMode.GmSurvivalCommand;
import com.michalovec.eventhelper.commands.Warp.CreateWarpCommand;
import com.michalovec.eventhelper.commands.Warp.RemoveWarpCommand;
import com.michalovec.eventhelper.commands.Warp.WarpCommand;
import com.michalovec.eventhelper.commands.rank.RankCommand;
import com.michalovec.eventhelper.commands.rank.UpdateCommand;
import com.michalovec.eventhelper.commands.teleport.TpAllCommand;
import com.michalovec.eventhelper.commands.teleport.TpHereCommand;
import com.michalovec.eventhelper.Listeners.ChatListener;
import com.michalovec.eventhelper.Listeners.JoinQuitListener;
import com.michalovec.eventhelper.Listeners.RankListener;
import com.michalovec.eventhelper.managers.rank.NameTagManager;
import com.michalovec.eventhelper.managers.rank.RankManager;
import com.michalovec.eventhelper.TabCompletetion.EventHelperTabCompleter;
import com.michalovec.eventhelper.TabCompletetion.TpHereTabCompleter;
import com.michalovec.eventhelper.TabCompletetion.WarpTabCompleter;
import com.michalovec.eventhelper.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    private static Main instance;

    private NameTagManager nameTagManager;
    private RankManager rankManager;

    @Override
    public void onEnable() {
        instance = this;

        nameTagManager = new NameTagManager(this);
        rankManager = new RankManager(this);



        // COMMANDS
        // WARP
        getCommand("createwarp").setExecutor(new CreateWarpCommand());
        getCommand("removewarp").setExecutor(new RemoveWarpCommand());
        getCommand("removewarp").setTabCompleter(new WarpTabCompleter());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warp").setTabCompleter(new WarpTabCompleter());

        // RANK
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("updateranks").setExecutor(new UpdateCommand(this));

        // EVENTHELPER
        getCommand("eventhelper").setExecutor(new EventHelperCommand());
        getCommand("eventhelper").setTabCompleter(new EventHelperTabCompleter());

        // TELEPORT
        getCommand("tpall").setExecutor(new TpAllCommand());
        getCommand("tphere").setExecutor(new TpHereCommand());
        getCommand("tphere").setTabCompleter(new TpHereTabCompleter());

        // GAMEMODE
        getCommand("gma").setExecutor(new GmAdventureCommand());
        getCommand("gmc").setExecutor(new GmCreativeCommand());
        getCommand("gmsp").setExecutor(new GmSpectatorCommand());
        getCommand("gms").setExecutor(new GmSurvivalCommand());

        // PLAYER LIST
        getCommand("list").setExecutor(new ListCommand());

        // LISTENERS

        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        // RANK
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

        File warpFolder = new File(getDataFolder(), "warps");
        if (!warpFolder.exists()) warpFolder.mkdirs();

        MessageManager.setup();
    }

    @Override
    public void onDisable() {

    }

    public NameTagManager getNametagManager() {
        return nameTagManager;
    }

    public RankManager getRankManager() {
        return rankManager;
    }

    public static Main getInstance() {
        return instance;
    }
}
