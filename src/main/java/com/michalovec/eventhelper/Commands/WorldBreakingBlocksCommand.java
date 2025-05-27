package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WorldBreakingBlocksCommand implements CommandExecutor {

    private Main main;

    public WorldBreakingBlocksCommand(Main main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            if (player == null) return true;

            HashMap<World, Boolean> worldBreaking = main.getBreakingEvent().getWorldAllowedBreaking();

            if ((Boolean.valueOf(args[0]) || !Boolean.valueOf(args[0])) && args.length == 1 && worldBreaking.containsKey(player.getWorld())){
                worldBreaking.replace(player.getWorld(), Boolean.valueOf(args[0]));
            } else if (args.length == 2 && (Boolean.valueOf(args[1]) || !Boolean.valueOf(args[1]))){
                World world = Bukkit.getWorld(args[0]);
                if (world == null) return true;
                if (worldBreaking.containsKey(world)){
                    worldBreaking.replace(world, Boolean.valueOf(args[1]));
                } else {
                    worldBreaking.put(world, Boolean.valueOf(args[1]));
                }
                player.sendMessage(ChatColor.GREEN + "Ničení blocků na světě: " + world.getName() + " bylo nastaveno na: " + args[1]);
            } else if (args.length == 1) {
                player.sendMessage("Na světě " + args[0] + " je nastaveno těžení na: " + worldBreaking.get(Bukkit.getWorld(args[0])));
            } else {
                player.sendMessage(ChatColor.RED + "Použij formát /wg <svět>");
                player.sendMessage(ChatColor.RED + "nebo /wg <svět> <true|false>");
                player.sendMessage(ChatColor.RED + "nebo /wg <true|false>");
            }


        }
        return false;
    }
}
