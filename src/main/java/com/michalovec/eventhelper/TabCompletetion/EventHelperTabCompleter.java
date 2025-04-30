package com.michalovec.eventhelper.TabCompletetion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventHelperTabCompleter implements TabCompleter {

    private final List<String> subCommands = Arrays.asList("reload", "version", "help");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            String current = args[0].toLowerCase();
            for (String sub : subCommands) {
                if (sub.startsWith(current)) {
                    completions.add(sub);
                }
            }
            return completions;
        }

        return null;
    }
}
