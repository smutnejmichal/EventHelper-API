package com.michalovec.eventhelper;

import com.michalovec.eventhelper.Commands.BlockedCommands.BlockedCommands;
import com.michalovec.eventhelper.Listeners.ChatListener;
import com.michalovec.eventhelper.Listeners.EventSettingsGuiListener;
import com.michalovec.eventhelper.Managers.TabUpdater;
import com.michalovec.eventhelper.TabCompletetion.RankTabCompleter;
import com.michalovec.eventhelper.Commands.*;
import com.michalovec.eventhelper.Commands.GameMode.GmAdventureCommand;
import com.michalovec.eventhelper.Commands.GameMode.GmCreativeCommand;
import com.michalovec.eventhelper.Commands.GameMode.GmSpectatorCommand;
import com.michalovec.eventhelper.Commands.GameMode.GmSurvivalCommand;
import com.michalovec.eventhelper.Commands.Warp.CreateWarpCommand;
import com.michalovec.eventhelper.Commands.Warp.RemoveWarpCommand;
import com.michalovec.eventhelper.Commands.Warp.WarpCommand;
import com.michalovec.eventhelper.Commands.Rank.RankCommand;
import com.michalovec.eventhelper.Commands.Rank.UpdateCommand;
import com.michalovec.eventhelper.Commands.Teleport.TpAllCommand;
import com.michalovec.eventhelper.Commands.Teleport.TpHereCommand;
import com.michalovec.eventhelper.Listeners.JoinQuitListener;
import com.michalovec.eventhelper.Listeners.RankListener;
import com.michalovec.eventhelper.Managers.Rank.NameTagManager;
import com.michalovec.eventhelper.Managers.Rank.RankManager;
import com.michalovec.eventhelper.TabCompletetion.EventHelperTabCompleter;
import com.michalovec.eventhelper.TabCompletetion.TpHereTabCompleter;
import com.michalovec.eventhelper.TabCompletetion.WarpTabCompleter;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    private static Main instance;

    private boolean chatEnabled = true;
    private NameTagManager nameTagManager;
    private RankManager rankManager;

    @Override
    public void onEnable() {
        instance = this;

        nameTagManager = new NameTagManager(this);
        rankManager = new RankManager(this);

        new TabUpdater(this).start();

        // COMMANDS
        // WARP
        getCommand("createwarp").setExecutor(new CreateWarpCommand());
        getCommand("removewarp").setExecutor(new RemoveWarpCommand());
        getCommand("removewarp").setTabCompleter(new WarpTabCompleter());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warp").setTabCompleter(new WarpTabCompleter());

        // GUI
        getCommand("eventsettings").setExecutor(new EventSettingsCommand(this));

        // RANK
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("rank").setTabCompleter(new RankTabCompleter());
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

        // TEAM CHAT + SPY
        getCommand("chat").setExecutor(new TeamChatCommand(this));

        // BLOCKED COMMANDS
        getCommand("say").setExecutor(new BlockedCommands());
        getCommand("say").setPermission("eventhelper.op");
        getCommand("tell").setPermission("eventhelper.op");
        getCommand("me").setPermission("eventhelper.op");

        // LISTENERS
        // RANK
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

        // GUI
        Bukkit.getPluginManager().registerEvents(new EventSettingsGuiListener(this),this);

        File warpFolder = new File(getDataFolder(), "warps");
        if (!warpFolder.exists()) warpFolder.mkdirs();

        MessageManager.setup();
    }

    @Override
    public void onDisable() {}

    public NameTagManager getNametagManager() {
        return nameTagManager;
    }

    public RankManager getRankManager() {
        return rankManager;
    }

    public static Main getInstance() {
        return instance;
    }

    public boolean isChatEnabled() {
        return chatEnabled;
    }

    public void toggleChatEnabled() {
        this.chatEnabled = !chatEnabled;
    }
}
