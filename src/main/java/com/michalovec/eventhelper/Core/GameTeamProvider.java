package com.michalovec.eventhelper.Core;

import org.bukkit.entity.Player;

public interface GameTeamProvider {
    GameTeam getTeam(Player player);
}
