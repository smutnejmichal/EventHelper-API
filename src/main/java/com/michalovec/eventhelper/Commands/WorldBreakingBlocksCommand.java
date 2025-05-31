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

            HashMap<World, Boolean> worldBreaking = main.getWorldBreakingManager().getWorldAllowedBreaking();

            if (args.length == 1){
                if (!args[0].equalsIgnoreCase("reload") && main.getWorldBreakingManager().isWorldValid(args[0])){ // formát /wg <svět>
                    if (worldBreaking.get(Bukkit.getWorld(args[0])) == null){
                        player.sendMessage("Nastavená hodnota je null. A bude nastavena na true");
                        main.getWorldBreakingManager().changeBreaking(Bukkit.getWorld(args[0]), true);
                        worldBreaking.remove(Bukkit.getWorld(args[0]));
                        worldBreaking.put(Bukkit.getWorld(args[0]), true);
                    }
                    player.sendMessage("Na světě " + args[0] + " je nastaveno těžení na: " + worldBreaking.get(Bukkit.getWorld(args[0])));
                } else if ((Boolean.parseBoolean(args[0]) || !Boolean.parseBoolean(args[0])) && worldBreaking.containsKey(player.getWorld()) && !args[0].equalsIgnoreCase("reload")){ // formát /wg <true | false>
                    worldBreaking.replace(player.getWorld(), Boolean.parseBoolean(args[0]));
                    main.getWorldBreakingManager().changeBreaking(player.getWorld(), Boolean.parseBoolean(args[0]));
                    player.sendMessage("Na světě " + player.getWorld().getName() + " je nastaveno těžení na: " + worldBreaking.get(player.getWorld()));
                } else if (args[0].equalsIgnoreCase("reload")){
                    player.sendMessage(ChatColor.GRAY + "Znovu načítám všechna práva ve světech ze souborů");
                    main.getWorldBreakingManager().reloadWorldsBreaking();
                }
                return false;
            }else if (args.length == 2 && (Boolean.parseBoolean(args[1]) || !Boolean.parseBoolean(args[1]))){ // formát /wg <svět> <true | false>
                World world = Bukkit.getWorld(args[0]);
                if (world == null) return true;
                if (worldBreaking.containsKey(world)){
                    worldBreaking.replace(world, Boolean.parseBoolean(args[1]));
                    main.getWorldBreakingManager().changeBreaking(player.getWorld(), Boolean.parseBoolean(args[1]));
                } else {
                    worldBreaking.put(world, Boolean.parseBoolean(args[1]));
                    main.getWorldBreakingManager().changeBreaking(player.getWorld(), Boolean.parseBoolean(args[1]));
                }
                player.sendMessage(ChatColor.GREEN + "Ničení blocků na světě: " + world.getName() + " bylo nastaveno na: " + args[1]);
                return false;
            }else if (args.length == 0){
                if (worldBreaking.get(player.getWorld()) == null){
                    player.sendMessage("Nastavená hodnota je null. A bude nastavena na true");
                    main.getWorldBreakingManager().changeBreaking(player.getWorld(), true);
                    worldBreaking.remove(player.getWorld());
                    worldBreaking.put(player.getWorld(), true);
                }
                player.sendMessage("Na tomto světě je nastaveno těžení na: " + worldBreaking.get(player.getWorld()));


            }else {
                player.sendMessage(ChatColor.RED + "Použij formát /wg <svět>");
                player.sendMessage(ChatColor.RED + "nebo /wg <svět> <true|false>");
                player.sendMessage(ChatColor.RED + "nebo /wg <true|false>");
                player.sendMessage(ChatColor.RED + "nebo /wg (zobrazí jaká je nastavená hodnota na tomto světě)");
            }
        }
        return false;
    }
}
