package com.michalovec.eventhelper.Placeholders;

import com.michalovec.eventhelper.Core.GameTeam;
import com.michalovec.eventhelper.Core.TeamAPI;
import com.michalovec.eventhelper.EventHelper;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.platform.facet.Facet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Placeholder extends PlaceholderExpansion {

    private final EventHelper plugin;

    public Placeholder(EventHelper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "eventhelper";
    }

    @Override
    public String getAuthor() {
        return "Michalovec";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equals("team_prefix")) {
            GameTeam team = TeamAPI.getTeam(player);
            if (team != null) return team.getDisplayName();
            return "%luckperms_prefix%";
        }

        if (identifier.equals("rank")) {
            int rank = plugin.getStatsManager().getWins(player.getName());
            return String.valueOf(rank);
        }

        if (identifier.startsWith("top")) {
            try {
                int pos = Integer.parseInt(identifier.substring(3)) -1;
                List<Map.Entry<String, Integer>> top = new ArrayList<>(plugin.getStatsManager().getTop10().entrySet());
                if (pos < top.size()) {
                    Map.Entry<String, Integer> entry = top.get(pos);
                    return ChatColor.YELLOW + entry.getKey() + " §f- " + ChatColor.YELLOW + entry.getValue() + ChatColor.WHITE + " výher";
                } else {
                    return ChatColor.GRAY + "NaN";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
