package de.luca.SpawnHealSystem.main;

import de.luca.SpawnHealSystem.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.luca.SpawnHealSystem.commands.HealCommand;
import de.luca.SpawnHealSystem.commands.SetspawnCommand;
import de.luca.SpawnHealSystem.commands.SpawnCommand;

public class Main extends JavaPlugin {
	private static final String PREFIX = "§8[§6Spawn-Heal-System§8] ";
	private static Main instance;

	//Plugin Aktiviert!

	public void onEnable() {
		instance = this;
		Bukkit.getConsoleSender().sendMessage("§7========================================================");
		Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "§8>> §aDas Spawn-Heal-System wurde aktiviert!");
		Bukkit.getConsoleSender().sendMessage("§7========================================================");

		//Command Registrieren!

		getCommand("heal").setExecutor(new HealCommand());
		getCommand("setspawn").setExecutor(new SetspawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new PlayerJoinListener(),this);
	}

	// Plugin Deaktiviert!

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§7========================================================");
		Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "§8>> §4Das Spawn-Heal-System wurde deaktiviert!");
		Bukkit.getConsoleSender().sendMessage("§7========================================================");
	}

	public static String getPrefix() {
		return PREFIX;
	}

	public static Main getInstance() {
		return instance;
	}

	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
