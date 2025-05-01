package com.michalovec.eventhelper.TabCompletetion;

import com.michalovec.eventhelper.Managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarpTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,String s,String[] args) {

        if (args.length == 1) {
            return WarpManager.getWarpNames().stream()
                    .filter(name -> name.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
