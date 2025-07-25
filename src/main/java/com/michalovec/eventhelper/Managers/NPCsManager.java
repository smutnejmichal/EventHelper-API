package com.michalovec.eventhelper.Managers;

import com.michalovec.eventhelper.EventHelper;
import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class NPCsManager {

    private final EventHelper plugin;

    public NPCsManager(EventHelper plugin) {
        this.plugin = plugin;
    }

    private void createNPC(String name, Location location, String color) {
        NpcData data = new NpcData(name, UUID.randomUUID(), location);
        data.setDisplayName(name);
        data.setScale(1.5f);
        data.setVisibilityDistance(100);
        data.setCollidable(false);
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(data);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.setSaveToFile(true);
        npc.spawnForAll();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc skin " + name + " " + name);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc glowing " + name + " " + color);
    }

    public void spawnFirstPlace(Player target) {
        createNPC(target.getName(), getLocationByPath("firstplace"), "gold");
        Bukkit.broadcastMessage("\n§x§C§A§B§9§2§F§lHráč " + target.getName() + " je na 1. místě!\n");
    }

    public void spawnSecondPlace(Player target) {
        createNPC(target.getName(), getLocationByPath("secondplace"), "gray");
        Bukkit.broadcastMessage("\n§7§lHráč " + target.getName() + " je na 2. místě!\n");
    }

    public void spawnThirdPlace(Player target) {
        createNPC(target.getName(), getLocationByPath("thirdplace"), "red");
        Bukkit.broadcastMessage("\n§x§C§8§8§5§0§A§lHráč " + target.getName() + " je na 3. místě!\n");
    }

    private Location getLocationByPath(String path) {
        FileConfiguration config = plugin.getConfig();

        return new Location(
                Bukkit.getWorld(config.getString("winners-locations.world", "Spawn")),
                config.getDouble("winners-locations." + path + ".x"),
                config.getDouble("winners-locations." + path + ".y"),
                config.getDouble("winners-locations." + path + ".z"),
                config.getInt("winners-locations." + path + ".yaw"),
                config.getInt("winners-locations." + path + ".pitch")
        );
    }

    public void spawnFireworksOnWinner(Player winner, int seconds) {
        new BukkitRunnable() {
            int timeLeft = seconds;

            @Override
            public void run() {

                Location loc = winner.getLocation();
                Firework firework = winner.getWorld().spawn(loc, Firework.class);
                FireworkMeta meta = (FireworkMeta) firework.getFireworkMeta();
                meta.addEffect(FireworkEffect.builder()
                        .withColor(Color.YELLOW)
                        .withFade(Color.WHITE)
                        .with(FireworkEffect.Type.BALL_LARGE)
                        .flicker(true)
                        .trail(true)
                        .build());

                meta.setPower(1);
                firework.setFireworkMeta(meta);
                firework.setMetadata("noDamage", new FixedMetadataValue(plugin, true));

                if (timeLeft <= 0) {
                    this.cancel();
                    return;
                }

                timeLeft--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

}
