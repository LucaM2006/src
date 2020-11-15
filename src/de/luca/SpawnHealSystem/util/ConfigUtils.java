package de.lucahdyt.SpawnHealSystem.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {
	
	public static File file = new File("plugins//Spawn-System//locations.yml");
	public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
	public static void savedata() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
