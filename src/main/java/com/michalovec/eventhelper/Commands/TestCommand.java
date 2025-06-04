package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.EventHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {

    private final EventHelper plugin;

    public TestCommand(EventHelper plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {



        plugin.getTestManager().setGameRunning();
        System.out.println(plugin.getTestManager().isGameRunning());


        return true;
    }
}
