import de.luca.SpawnhealSystem.commands.HealCommand;
import de.luca.SpawnhealSystem.commands.SetspawnCommand;
import de.luca.SpawnhealSystem.commands.SpawnCommand;
import de.luca.SpawnhealSystem.scoreboard.PlayerJoinListener;
import de.luca.SpawnhealSystem.scoreboard.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static String PREFIX = "§6Spawn-Heal-System§8 ";

    //Plugin Aktiviert!

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§8>> §aDas Spawn-Heal-System wird geladen...");
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        try {
            register();
        } catch (Exception e1) {
            Bukkit.getConsoleSender().sendMessage("§7========================================================");
            Bukkit.getConsoleSender().sendMessage(getPrefix() + "§8>> §4Das Spawn-Heal-System hat einen Fehler!");
            Bukkit.getConsoleSender().sendMessage("§7========================================================");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§8>> §aDas Spawn-Heal-System wurde erfolgreich geladen!");
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
    }

    //Commands Registrieren!

    public void register() {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("setspawn").setExecutor(new SetspawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        //Listeners Registrieren!

        PluginManager pluginManager = Bukkit.getPluginManager();
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        (new ScoreboardHandler()).startAnimation();
    }

    //Plugin Deaktiviert!

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§8>> §4Das Spawn-Heal-System wurde gestoppt!");
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
    }

    //Prefix Registrieren!

    public static String getPrefix() {
        return PREFIX;
    }

    //Configs Registrieren!

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
