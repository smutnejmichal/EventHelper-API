    package com.michalovec.eventhelper.managers;

    import com.michalovec.eventhelper.Main;
    import org.bukkit.Bukkit;
    import org.bukkit.Location;
    import org.bukkit.World;
    import org.bukkit.configuration.file.YamlConfiguration;

    import java.io.File;
    import java.io.IOException;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.List;
    import java.util.stream.Collectors;

    public class WarpManager {

    private static final File warpFolder = new File(Main.getInstance().getDataFolder(), "warps");

    public static void createWarp(String name, Location location) throws IOException {
        File warpFile = new File(warpFolder, name + ".yml");
        if (!warpFile.exists()) warpFile.createNewFile();

        YamlConfiguration config = YamlConfiguration.loadConfiguration(warpFile);
        config.set("world", location.getWorld().getName());
        config.set("x", location.getX());
        config.set("y", location.getY());
        config.set("z", location.getZ());
        config.set("yaw", location.getYaw());
        config.set("pitch", location.getPitch());
        config.save(warpFile);
    }

    public static Location getWarp(String name) {
        File warpFile = new File(warpFolder, name + ".yml");
        if (!warpFile.exists()) return null;

        YamlConfiguration config = YamlConfiguration.loadConfiguration(warpFile);
        World world = Bukkit.getWorld(config.getString("world"));
        double x = config.getDouble("x");
        double y = config.getDouble("y");
        double z = config.getDouble("z");
        float yaw = (float) config.getDouble("yaw");
        float pitch = (float) config.getDouble("pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public static List<String> getWarpNames() {
        if (!warpFolder.exists()) return Collections.emptyList();
        String[] files = warpFolder.list((dir, name) -> name.endsWith(".yml"));
        if (files == null) return Collections.emptyList();

        return Arrays.stream(files)
                .map(name -> name.replace(".yml", ""))
                .collect(Collectors.toList());
    }

    public static boolean warpExists(String name) {
        File warpFile = new File(warpFolder, name + ".yml");
        return warpFile.exists();
    }

}
