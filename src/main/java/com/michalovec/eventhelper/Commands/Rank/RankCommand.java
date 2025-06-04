package com.michalovec.eventhelper.Commands.Rank;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.EventHelper;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {


    //     /rank <player> <rank>

    private EventHelper main;

    public RankCommand(EventHelper main){
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;

            if (player.isOp()){
                if (args.length == 2){
                    if (Bukkit.getPlayer(args[0]) != null){
                        Player target = Bukkit.getPlayer(args[0]);

                        for (Rank rank : Rank.values()){
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                main.getRankManager().setRank(target.getUniqueId(),rank,false);

                                player.sendMessage(MessageManager.getRankMessage("messages.rankChanged", target, rank.getDisplay()));
                                if (target.isOnline()){
                                    target.sendMessage(MessageManager.getRankMessage("messages.rankSomeoneChanged", player, rank.getDisplay()));
                                }
                                return false;
                            }
                        }
                        player.sendMessage(MessageManager.get("messages.unknownRank"));

                    } else {
                        player.sendMessage(MessageManager.getWithWarp("messages.playerNotOnline", args[0]));
                    }
                } else {
                    player.sendMessage(MessageManager.get("messages.rankWrongUsage"));
                }
            } else {
                player.sendMessage(MessageManager.get("messages.playerIsNotOP"));
            }


        }
        return false;
    }
}
