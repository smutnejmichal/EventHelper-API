package com.michalovec.eventhelper.Managers;

import com.michalovec.eventhelper.EventHelper;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class MessageManager {
    private static File file;
    private static YamlConfiguration config;

    public static void setup() {
        file = new File(EventHelper.getInstance().getDataFolder(), "messages.yml");

        if (!file.exists()) {
            EventHelper.getInstance().saveResource("messages.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static String get(String path) {
        String raw = config.getString(path, "§c[Missing message: " + path + "]");
        raw = raw.replace("%prefix%", getPrefix());
        return raw;
    }

    public static String getPrefix() {
        return config.getString("prefix", "§x§F§B§0§8§0§8E§x§F§1§0§D§0§Dv§x§E§6§1§1§1§1e§x§D§C§1§6§1§6n§x§D§2§1§B§1§Bt§x§C§8§2§0§2§0H§x§B§D§2§4§2§4e§x§B§3§2§9§2§9l§x§A§9§2§E§2§Ep§x§9§E§3§2§3§2e§x§9§4§3§7§3§7r §f•");
    }

    public static String getWithWarp(String path, String warpName) {
        return get(path).replace("%warp%", warpName);
    }

    public static String getRankMessage(String path, Player player, String rank) {
        return get(path).replace("%player%", player.getName()).replace("%rank%", rank);
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
