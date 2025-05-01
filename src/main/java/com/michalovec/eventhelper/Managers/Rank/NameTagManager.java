package com.michalovec.eventhelper.Managers.Rank;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class NameTagManager {

    private Main main;
    private Player player;
    public NameTagManager(Main main){
        this.main = main;
    }

    public void setNametags(Player player){

        Scoreboard board = player.getScoreboard();

        if (board == null || board == Bukkit.getScoreboardManager().getMainScoreboard()) {
            board = Bukkit.getScoreboardManager().getNewScoreboard();
        }

        for (Rank rank : Rank.values()) {
            Team team = board.getTeam(rank.name());

            if (team == null) {
                team = board.registerNewTeam(rank.name());
            }

            team.setPrefix(rank.getDisplay() + " ");
        }

        for (Player target : Bukkit.getOnlinePlayers()) {
            Rank targetRank = main.getRankManager().getRank(target.getUniqueId());
            Team team = board.getTeam(targetRank.name());

            if (team != null) {
                team.addEntry(target.getName());
            }
        }

        player.setScoreboard(board);
    }

    public void newTag(Player player){
        Rank rank = main.getRankManager().getRank(player.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()){

            target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }
    }

    public void removeTag(Player player){
        for (Player target : Bukkit.getOnlinePlayers()){
            if (target.getScoreboard().getEntryTeam(player.getName()) == null){
                return;
            }

            Objects.requireNonNull(target.getScoreboard().getEntryTeam(player.getName())).removeEntry(player.getName());
        }
    }






}
