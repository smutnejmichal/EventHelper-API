package com.michalovec.eventhelper.Managers;

import com.michalovec.eventhelper.EventHelper;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StatsManager {
    private final File file;
    private final FileConfiguration config;

    public StatsManager(EventHelper plugin) {
        this.file = new File(plugin.getDataFolder(), "wins.yml");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileConfiguration emptyConfig = new YamlConfiguration();
                emptyConfig.set("players", new HashMap<String, Integer>());
                emptyConfig.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public int getWins(String name) {
        return config.getInt("players." + name, 0);
    }

    public void addWin(String name) {
        int wins = getWins(name) + 1;
        config.set("players." + name, wins);
        save();
    }

    public Map<String, Integer> getTop10() {
        ConfigurationSection section = config.getConfigurationSection("players");
        if (section == null || section.getKeys(false).isEmpty()) {
            return Collections.emptyMap();
        }

        return section.getKeys(false).stream()
                .collect(Collectors.toMap(
                        name -> name,
                        this::getWins
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearAll() {
        if (config.contains("players")) {
            config.set("players", null);
            save();
        }
    }


}
