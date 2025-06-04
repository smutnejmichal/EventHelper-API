package com.michalovec.eventhelper.Core;

import java.util.UUID;


public class TeamAPI {
    private static GameTeamProvider currentProvider = null;

    public static void setProvider(GameTeamProvider provider) {
        currentProvider = provider;
    }

    public static void clearProvider() {
        currentProvider = null;
    }

    public static GameTeam getTeam(UUID playerUUID) {
        return currentProvider != null ? currentProvider.getTeam(playerUUID) : null;
    }
}
