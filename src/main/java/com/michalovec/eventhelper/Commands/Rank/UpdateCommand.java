package com.michalovec.eventhelper.Commands.Rank;

import com.michalovec.eventhelper.EventHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UpdateCommand implements CommandExecutor {

    private EventHelper main;

    public UpdateCommand(EventHelper main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        for (Player player : Bukkit.getOnlinePlayers()){
            main.getNametagManager().setNametags(player);
        }

        return false;
    }
}
