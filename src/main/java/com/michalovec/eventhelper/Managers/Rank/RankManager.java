package com.michalovec.eventhelper.Managers.Rank;

import com.michalovec.eventhelper.Enum.Rank;
import com.michalovec.eventhelper.EventHelper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private EventHelper main;

    private File file;
    private YamlConfiguration config;
    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();


    public RankManager(EventHelper main){
        this.main = main;

        if (!main.getDataFolder().exists()){
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "rank.yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setRank(UUID uuid, Rank rank, boolean firstJoin){
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin){
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid)){
                attachment = perms.get(uuid);
            }else {
                attachment = player.addAttachment(main);
                perms.put(uuid, attachment);
            }

            for (String perm : getRank(uuid).getPermissions()){
                if (player.hasPermission(perm)){
                    attachment.unsetPermission(perm);
                }
            }

            for (String perm : rank.getPermissions()){
                attachment.setPermission(perm, true);
            }


        }


        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Bukkit.getOfflinePlayer(uuid).isOnline()){
            Player player = Bukkit.getPlayer(uuid);
            main.getNametagManager().removeTag(player);
            assert player != null;
            main.getNametagManager().newTag(player);
        }


    }

    public static <T extends Enum<T>> T findEnumValue(Class<T> type, String name) {
        if (name == null)
            return null;
        try {
            return Enum.valueOf(type, name.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }

    public Rank getRank(UUID uuid){ return findEnumValue(Rank.class, config.getString(uuid.toString()));}

    public HashMap<UUID, PermissionAttachment> getPerms() {return perms;}

    public YamlConfiguration getConfig() {return config;}

    public File getFile() {return file;}
}
