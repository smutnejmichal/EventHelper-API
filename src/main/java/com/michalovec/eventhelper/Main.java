package com.michalovec.eventhelper;

import com.michalovec.eventhelper.Listeners.*;
import com.michalovec.eventhelper.Managers.TabUpdater;
import com.michalovec.eventhelper.Managers.WorldBreakingManager;
import com.michalovec.eventhelper.TabCompletetion.*;
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
import com.michalovec.eventhelper.Managers.Rank.NameTagManager;
import com.michalovec.eventhelper.Managers.Rank.RankManager;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    private static Main instance;

    private boolean chatEnabled = true;
    private NameTagManager nameTagManager;
    private RankManager rankManager;
    private WorldBreakingManager worldBreakingManager;

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

        nameTagManager = new NameTagManager(this);
        rankManager = new RankManager(this);
        worldBreakingManager = new WorldBreakingManager(this);

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
        getCommand("tphere").setTabCompleter(new PlayerCompleter());

        // GAMEMODE
        getCommand("gma").setExecutor(new GmAdventureCommand());
        getCommand("gmc").setExecutor(new GmCreativeCommand());
        getCommand("gmsp").setExecutor(new GmSpectatorCommand());
        getCommand("gms").setExecutor(new GmSurvivalCommand());

        // PLAYER LIST
        getCommand("list").setExecutor(new ListCommand());

        // TEAM CHAT + SPY
        getCommand("chat").setExecutor(new TeamChatCommand(this));

        // World Block Breaking
        getCommand("worldgriefing").setExecutor(new WorldBreakingBlocksCommand(this));
        getCommand("worldgriefing").setTabCompleter(new WorldBreakingBlocksTabCompleter());

        // HEAL
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("heal").setTabCompleter(new PlayerCompleter());

        // FEED
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("feed").setTabCompleter(new PlayerCompleter());

        // LISTENERS
        // RANK
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

        // GUI
        Bukkit.getPluginManager().registerEvents(new EventSettingsGuiListener(this),this);

        //World Block Breaking
        Bukkit.getPluginManager().registerEvents(new WorldBreakingEvent(this), this);

        //World Block Breaking Loading
        worldBreakingManager.loadWorldsBreaking();

        getLogger().info("Loading configuration...");
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

    public WorldBreakingManager getWorldBreakingManager() {
        return worldBreakingManager;
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
