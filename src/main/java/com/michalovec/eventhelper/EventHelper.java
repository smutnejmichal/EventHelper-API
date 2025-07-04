package com.michalovec.eventhelper;

import com.michalovec.eventhelper.Listeners.*;
import com.michalovec.eventhelper.Managers.*;
import com.michalovec.eventhelper.Placeholders.Placeholder;
import com.michalovec.eventhelper.TabCompletetion.*;
import com.michalovec.eventhelper.Commands.*;
import com.michalovec.eventhelper.Commands.GameMode.GmAdventureCommand;
import com.michalovec.eventhelper.Commands.GameMode.GmCreativeCommand;
import com.michalovec.eventhelper.Commands.GameMode.GmSpectatorCommand;
import com.michalovec.eventhelper.Commands.GameMode.GmSurvivalCommand;
import com.michalovec.eventhelper.Commands.Warp.CreateWarpCommand;
import com.michalovec.eventhelper.Commands.Warp.RemoveWarpCommand;
import com.michalovec.eventhelper.Commands.Warp.WarpCommand;
import com.michalovec.eventhelper.Commands.Teleport.TpAllCommand;
import com.michalovec.eventhelper.Commands.Teleport.TpHereCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class EventHelper extends JavaPlugin {

    private static EventHelper instance;

    private boolean chatEnabled = true;
    private boolean isAnyGameRunning = false;
    private StatsManager statsManager;

    @Override
    public void onEnable() {
        String RED = "\u001B[91m";
        String DARK_RED = "\u001B[31m";
        String ORANGE = "\u001B[33m";
        String GRAY = "\u001B[90m";

        System.out.print(RED + "   __" + "\n");
        System.out.print(RED + "  |__ " + DARK_RED + "|__|   " + ORANGE + "EventHelper " + RED + "v" + getDescription().getVersion() + "\n");
        System.out.print(RED + "  |__ " + DARK_RED + "|  |   " + GRAY + "Running on Bukkit - " + getServer().getName() + "\n");
        System.out.println();
        instance = this;

        statsManager = new StatsManager(this);

        // COMMANDS
        // WARP
        getLogger().info("Registering commands...");
        getCommand("createwarp").setExecutor(new CreateWarpCommand());
        getCommand("removewarp").setExecutor(new RemoveWarpCommand());
        getCommand("removewarp").setTabCompleter(new WarpTabCompleter());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warp").setTabCompleter(new WarpTabCompleter());

        // GUI
        getCommand("eventsettings").setExecutor(new EventSettingsCommand(this));

        // EVENTHELPER
        getCommand("eventhelper").setExecutor(new EventHelperCommand());
        getCommand("eventhelper").setTabCompleter(new EventHelperTabCompleter());

        // TELEPORT
        getCommand("tpall").setExecutor(new TpAllCommand());
        getCommand("tphere").setExecutor(new TpHereCommand());
        getCommand("tphere").setTabCompleter(new PlayerCompleter());

        // GAMEMODE
        getCommand("gma").setExecutor(new GmAdventureCommand());
        getCommand("gmc").setExecutor(new GmCreativeCommand());
        getCommand("gmsp").setExecutor(new GmSpectatorCommand());
        getCommand("gms").setExecutor(new GmSurvivalCommand());

        getCommand("win").setExecutor(new WinCommand(this));

        // PLAYER LIST
        getCommand("list").setExecutor(new ListCommand());

        // HEAL
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("heal").setTabCompleter(new PlayerCompleter());

        // FEED
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("feed").setTabCompleter(new PlayerCompleter());

        // SPEED
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("feed").setTabCompleter(new SpeedTabCompleter());

        // TELEPORTALLTOSPAWN
        getCommand("tpalltospawn").setExecutor(new TeleportAllToSpawnCommand());

        // PLACEHOLDER
        getLogger().info("Registering placeholder...");
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Placeholder(this).register();
        } else {
            getLogger().warning("PlayerholderAPI nebylo nalezeno, placeholder nebude fungovat!");
        }

        // LISTENERS
        getLogger().info("Registering all listeners...");
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

        // GUI
        Bukkit.getPluginManager().registerEvents(new EventSettingsGuiListener(this),this);


        getLogger().info("Loading warps...");
        File warpFolder = new File(getDataFolder(), "warps");
        if (!warpFolder.exists()) warpFolder.mkdirs();

        getLogger().info("Loading messages file...");
        MessageManager.setup();
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye! \\o");
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }

    public static EventHelper getInstance() {
        return instance;
    }

    public boolean isChatEnabled() {
        return chatEnabled;
    }

    public void toggleChatEnabled() {
        this.chatEnabled = !chatEnabled;
    }

    public void setGameRunning(boolean anyGameRunning) {
        isAnyGameRunning = anyGameRunning;
    }

    public boolean isAnyGameRunning() {
        return isAnyGameRunning;
    }
}
