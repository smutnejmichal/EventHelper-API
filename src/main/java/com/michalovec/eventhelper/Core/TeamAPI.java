package com.michalovec.eventhelper.Core;

import org.bukkit.entity.Player;

import java.util.UUID;


public class TeamAPI {
    private static GameTeamProvider currentProvider = null;

    public static void setProvider(GameTeamProvider provider) {
        currentProvider = provider;
    }

    public static void clearProvider() {
        currentProvider = null;
    }

    public static GameTeam getTeam(Player player) {
        return currentProvider != null ? currentProvider.getTeam(player) : null;
    }
}
