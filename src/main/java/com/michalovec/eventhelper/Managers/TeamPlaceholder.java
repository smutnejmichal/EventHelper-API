package com.michalovec.eventhelper.Managers;

import com.michalovec.eventhelper.Core.GameTeam;
import com.michalovec.eventhelper.Core.TeamAPI;
import com.michalovec.eventhelper.EventHelper;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class TeamPlaceholder extends PlaceholderExpansion {

    private final EventHelper plugin;

    public TeamPlaceholder(EventHelper plugin) {
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
//            if (plugin.getTestManager().isGameRunning()) {
                GameTeam team = TeamAPI.getTeam(player);
                if (team != null) return team.getDisplayName();
//            } else {
                return "%luckperms_prefix%";
//            }
        }
        return null;
    }

}
