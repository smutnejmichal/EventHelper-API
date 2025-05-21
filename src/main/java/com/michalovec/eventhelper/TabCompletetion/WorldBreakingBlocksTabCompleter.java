package com.michalovec.eventhelper.TabCompletetion;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldBreakingBlocksTabCompleter implements TabCompleter {

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
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("true", "false"), new ArrayList<>());
        }
        return new ArrayList<>();
    }





}
