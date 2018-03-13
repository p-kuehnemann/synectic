package de.phylixit.aiohub.skywars.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocationManager {
    private static Map<String, Location> locations = new HashMap<>();
    private static File file = new File("plugins/skywars", "locations.yml");
    private static YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

    public static void setLocation(String name, Location location) {
        yamlConfiguration.set(name + ".World", location.getWorld().getName());
        yamlConfiguration.set(name + ".X", location.getX());
        yamlConfiguration.set(name + ".Y", location.getY());
        yamlConfiguration.set(name + ".Z", location.getZ());
        yamlConfiguration.set(name + ".Yaw", location.getYaw());
        yamlConfiguration.set(name + ".Pitch", location.getPitch());
        try { yamlConfiguration.save(file); } catch (IOException e) { e.printStackTrace(); }
    }
    public static boolean isLocationSet(String locationName) { return yamlConfiguration.contains(locationName); }
    public static Location getLocation(String name) { return locations.get(name); }
    public static Location getLocationConfig(String name) {
        World world = Bukkit.getWorld(yamlConfiguration.getString(name.toLowerCase() + ".World"));
        double x = yamlConfiguration.getDouble(name.toLowerCase() + ".X");
        double y = yamlConfiguration.getDouble(name.toLowerCase() + ".Y");
        double z = yamlConfiguration.getDouble(name.toLowerCase() + ".Z");
        double yaw = yamlConfiguration.getDouble(name.toLowerCase() + ".Yaw");
        double pitch = yamlConfiguration.getDouble(name.toLowerCase() + ".Pitch");
        Location location = new Location(world, x, y, z);
        location.setYaw((float) yaw);
        location.setPitch((float) pitch);
        return location;
    }
}
