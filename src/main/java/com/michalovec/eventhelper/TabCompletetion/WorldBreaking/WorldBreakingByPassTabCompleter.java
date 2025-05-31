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
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        } else if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("removeAll", "remove", "add"), new ArrayList<>());
        } else if (args.length == 3) {
            List<String> nameOfMaterial = new ArrayList<>();
            for (Material material : Material.values()){
                if (material.isBlock()){
                    String name = material.name().toLowerCase();
                    nameOfMaterial.add(name);
                }
            }
            return StringUtil.copyPartialMatches(args[2], nameOfMaterial, new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
