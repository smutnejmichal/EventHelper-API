package com.michalovec.eventhelper.Core;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CoreAPI {

    private static final Map<UUID, GameTeam> teamMap = new HashMap<>();

    public static void setPlayerTeam(Player player, GameTeam team) {
        teamMap.put(player.getUniqueId(), team);
    }

    public static String getTeamPrefix(Player player) {
        GameTeam team = teamMap.get(player.getUniqueId());
        return team != null ? team.getDisplayName() : null;
    }

    public static boolean isInGame(Player player) {
        return teamMap.containsKey(player.getUniqueId());
    }

    public static void clear() {
        teamMap.clear();
    }
}
