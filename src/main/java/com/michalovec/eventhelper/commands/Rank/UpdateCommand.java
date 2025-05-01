package com.michalovec.eventhelper.commands.Rank;

import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UpdateCommand implements CommandExecutor {

    private Main main;

    public UpdateCommand(Main main){
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
