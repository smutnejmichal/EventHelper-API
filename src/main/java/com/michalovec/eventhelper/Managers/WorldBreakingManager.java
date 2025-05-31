package com.michalovec.eventhelper.Managers;

import com.michalovec.eventhelper.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class WorldBreakingManager {

    private Main main;
    private File file;
    private YamlConfiguration config;

    private final HashMap<World, Boolean> worldAllowedBreaking = new HashMap<>();

    public WorldBreakingManager(Main main){
        this.main = main;

        if (!main.getDataFolder().exists()){
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "worldbreaking.yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    public void changeBreaking(World world, boolean breaking){
        config.set("worlds." + world.getName() + ".breaking", breaking);
        saveConfig();
    }

    public void addWorld(World world, boolean breaking){

        if (world.getName().equalsIgnoreCase("reload") || world.getName().equalsIgnoreCase("false") || world.getName().equalsIgnoreCase("true")){
            main.getLogger().warning("Svět se nemůže rovnat " + world.getName());
            return;
        }

        config.set("worlds." + world.getName() + ".uuid", world.getUID().toString());
        config.set("worlds." + world.getName() + ".breaking", breaking);

        saveConfig();
    }

    public void loadWorldsBreaking(){

        ConfigurationSection section = config.getConfigurationSection("worlds");
        if (section == null) return;

        for (String str : section.getKeys(false)){

            World world = Bukkit.getWorld(str);
            if (world == null){
                removeWorld(str);
                return;
            }

            boolean value = config.getBoolean("worlds." + str + ".breaking");
            worldAllowedBreaking.put(world, value);

        }
    }

    public boolean isWorldValid(String worldName){

        for (World world : Bukkit.getWorlds()){
            String name = world.getName();
            if (worldName.equals(name)){
                return true;
            }
        }

        return false;
    }

    public void reloadWorldsBreaking(){
        worldAllowedBreaking.clear();

        for (World world : Bukkit.getWorlds()){
            if (!config.contains("worlds." + world.getName())){
                addWorld(world, true);
            }
        }

        loadWorldsBreaking();
    }

    public void removeWorld(String name){
        config.set("worlds." + name, null);
        saveConfig();
    }

    public void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<World, Boolean> getWorldAllowedBreaking() {
        return worldAllowedBreaking;
    }
}
