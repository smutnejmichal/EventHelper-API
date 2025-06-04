package com.michalovec.eventhelper.Commands.WorldBreaking;

import com.michalovec.eventhelper.EventHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class WorldBreakingByPassCmd implements CommandExecutor {

    private EventHelper main;

    public WorldBreakingByPassCmd(EventHelper main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            if (player == null) return true;

            HashMap<World, List<String>> materials = main.getWorldBreakingManager().getBypassBreaking();

            if (args.length == 0){ // /wgbypass
                List<String> namesOfMaterials = materials.get(player.getWorld());
                player.sendMessage("Na tomto světe je povoleno těžit následující bloky: " + namesOfMaterials);
            } else if (args.length == 1) { // /wgbypass <svět> nebo /wgbypass removeAll
                if (args[0].equalsIgnoreCase("removeAll")){
                    main.getWorldBreakingManager().removeAll(player.getWorld());
                    player.sendMessage(ChatColor.GREEN + "Úspěšně byli odebrány všechny blocky z tohoto světa");
                } else if (args[0].equalsIgnoreCase("remove")) {
                    player.sendMessage(ChatColor.RED + "Použij prosím formát /wgbypass remove <block>");
                } else if (args[0].equalsIgnoreCase("add")) {
                    player.sendMessage(ChatColor.RED + "Použij prosím formát /wgbypass add <block>");
                } else {
                    World world = Bukkit.getWorld(args[0]);
                    if (world == null) {
                        player.sendMessage(ChatColor.RED + "Svět " + args[0] + " neexistuje");
                        return true;
                    }
                    List<String> namesOfMaterials = materials.get(world);
                    player.sendMessage("Na tomto světe je povoleno těžit následující bloky: " + namesOfMaterials);
                }
            } else if (args.length == 2) { // /wgbypass <svět> removeAll

                if (args[0].equalsIgnoreCase("remove")){
                    main.getWorldBreakingManager().removeBlock(player.getWorld(), args[1].toUpperCase());
                    player.sendMessage(ChatColor.GREEN + "Na světe " + args[0] + " byl úspěšně odebrán block " + args[1].toUpperCase());
                } else if (args[0].equalsIgnoreCase("add")) {
                    main.getWorldBreakingManager().addBlock(player.getWorld(), args[1].toUpperCase());
                    player.sendMessage(ChatColor.GREEN + "Na světe " + args[0] + " byl úspěšně přidán block " + args[1].toUpperCase());
                } else {
                    if (args[1].equalsIgnoreCase("removeAll")){
                        World world = Bukkit.getWorld(args[0]);
                        if (world == null) {
                            player.sendMessage(ChatColor.RED + "Svět " + args[0] + " neexistuje");
                            return true;
                        }
                        main.getWorldBreakingManager().removeAll(world);
                        player.sendMessage(ChatColor.GREEN + "Úspěšně byli odebrány všechny blocky");
                    } else if (args[1].equalsIgnoreCase("remove")) {
                        player.sendMessage(ChatColor.RED + "Použij prosím formát /wgbypass <svět> remove <block>");
                    } else if (args[1].equalsIgnoreCase("add")) {
                        player.sendMessage(ChatColor.RED + "Použij prosím formát /wgbypass <svět> add <block>");
                    } else {
                        player.sendMessage(ChatColor.RED + "Použij prosím formát /wgbypass");
                        player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět>");
                        player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět> removeAll");
                        player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět> <add | remove> <block>");
                    }
                }
            } else if (args.length == 3) { // /wgbypass <svět> <add | remove> <block>
                if (args[1].equalsIgnoreCase("remove")){
                    World world = Bukkit.getWorld(args[0]);
                    if (world == null) {
                        player.sendMessage(ChatColor.RED + "Svět " + args[0] + " neexistuje");
                        return true;
                    }
                    main.getWorldBreakingManager().removeBlock(world, args[2].toUpperCase());
                    player.sendMessage(ChatColor.GREEN + "Na světe " + args[0] + " byl úspěšně odebrán block " + args[2].toUpperCase());
                } else if (args[1].equalsIgnoreCase("add")){
                    World world = Bukkit.getWorld(args[0]);
                    if (world == null) {
                        player.sendMessage(ChatColor.RED + "Svět " + args[0] + " neexistuje");
                        return true;
                    }
                    main.getWorldBreakingManager().addBlock(world, args[2].toUpperCase());
                    player.sendMessage(ChatColor.GREEN + "Na světe " + args[0] + " byl úspěšně přidán block " + args[2].toUpperCase());
                }  else {
                    player.sendMessage(ChatColor.RED + "Použij prosím formát /wgbypass");
                    player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět>");
                    player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět> removeAll");
                    player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět> <add | remove> <block>");
                }

            }  else {
                player.sendMessage(ChatColor.RED + "Použij prosím formát /wgbypass");
                player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět>");
                player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět> removeAll");
                player.sendMessage(ChatColor.RED + "nebo /wgbypass <svět> <add | remove> <block>");
            }


        }


        return false;
    }
}
