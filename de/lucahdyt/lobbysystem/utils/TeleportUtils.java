package de.lucahdyt.lobbysystem.utils;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeleportUtils {
    private static File file;

    private static YamlConfiguration cfg;

    public static HashMap<String, Location> locationHashMap = new HashMap<>();

    public TeleportUtils(LobbySystem lobbySystem) {
        file = new File("plugins//Lobby-System//locatoins.yml");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        try {
            cfg = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void save(String name, Material material, int slot, Location location) {
        List<String> list;
        String toSave = name + ":" + material.toString() + ":" + slot + ":" + location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
        try {
            list = cfg.getStringList("teleporter");
        } catch (Exception e1) {
            list = new ArrayList<>();
        }
        list.add(toSave);
        cfg.set("teleporter", list);
        try {
            cfg.save(file);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }

    public static List<String> getWarps() {
        try {
            return getCfg().getStringList("teleporter");
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }
}
