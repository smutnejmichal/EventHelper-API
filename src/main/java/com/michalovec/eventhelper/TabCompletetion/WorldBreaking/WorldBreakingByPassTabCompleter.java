package com.michalovec.eventhelper.TabCompletetion.WorldBreaking;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldBreakingByPassTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            for (World world : Bukkit.getWorlds()){
                String name = world.getName();
                names.add(name);
            }
            names.add("removeAll");
            names.add("remove");
            names.add("add");
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("removeAll")) return new ArrayList<>();
            if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("add")){
                List<String> nameOfMaterial = new ArrayList<>();
                for (Material material : Material.values()){
                    if (material.isBlock()){
                        String name = material.name().toLowerCase();
                        nameOfMaterial.add(name);
                    }
                }
                return StringUtil.copyPartialMatches(args[1], nameOfMaterial, new ArrayList<>());
            }
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("removeAll", "remove", "add"), new ArrayList<>());
        } else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("add")){
                List<String> nameOfMaterial = new ArrayList<>();
                for (Material material : Material.values()){
                    if (material.isBlock()){
                        String name = material.name().toLowerCase();
                        nameOfMaterial.add(name);
                    }
                }
                return StringUtil.copyPartialMatches(args[2], nameOfMaterial, new ArrayList<>());
            } else {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}
