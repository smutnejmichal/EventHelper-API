package com.michalovec.eventhelper.commands.rank;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {


    //     /rank <player> <rank>

    private Main main;

    public RankCommand(Main main){
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
                            if (rank.name().equalsIgnoreCase(args[1])){ //equalsignorecase znamená že je jedno jak to je napsaný např. Admin, aDmin atd.
                                main.getRankManager().setRank(target.getUniqueId(),rank,false);

                                player.sendMessage(ChatColor.GREEN + "Změnil jsi " + target.getName() + " rank na " + rank.getDisplay() + ChatColor.GREEN + ".");
                                if (target.isOnline()){
                                    target.getPlayer().sendMessage(ChatColor.GREEN + player.getName() + " nastavil tvůj rank na " + rank.getDisplay() + ChatColor.GREEN + ".");
                                }
                                return false;
                            }
                        }
                        player.sendMessage(ChatColor.GOLD + "EVENT" + ChatColor.WHITE + " | " + ChatColor.RED + "Špatný rank! Použitelné ranky: Guest (nezařazeno) nebo Admin");

                    } else {
                        player.sendMessage(ChatColor.RED + "Tento uživatel nikdy nenapojil tento server!");
                    }
                }else {
                    player.sendMessage(ChatColor.RED + "Špatné použití! Prosím použij /rank <player> <rank>");
                }
            }else {
                player.sendMessage(ChatColor.RED + "Musíš mít OP pro tento command!");
            }


        }





        return false;
    }
}
