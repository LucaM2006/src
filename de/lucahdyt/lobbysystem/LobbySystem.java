package de.lucahdyt.lobbysystem;

import de.lucahdyt.lobbysystem.commands.*;
import de.lucahdyt.lobbysystem.listeners.*;
import de.lucahdyt.lobbysystem.utils.ScoreBoardManager;
import de.lucahdyt.lobbysystem.utils.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {

    public static String prefix, noPlayer, noPerm, use, world;

    public static LobbySystem instance;
    private Player player;

    private static TeleportUtils teleportUtils;
    private static ScoreBoardManager scoreBoardManager;

    //Plugin wird Aktiviert!

    public void onEnable() {

        instance = this;

        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§aDas Lobby-System wird geladen...");
        Bukkit.getConsoleSender().sendMessage("§7========================================================");

        //Plugin Fehler Ausgabe!

        try {
            register();
        } catch (Exception e1) {
            Bukkit.getConsoleSender().sendMessage("§7========================================================");
            Bukkit.getConsoleSender().sendMessage(getPrefix() + "§cDas Lobby-System hat einen Fehler!");
            Bukkit.getConsoleSender().sendMessage("§7========================================================");
            e1.printStackTrace();
            return;
        }

        //Plugin Erfolgreich Aktiviert!

        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§aDas Lobby-System wurde erfolgreich geladen!");
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        scoreBoardManager.startAnimation();
    }

    public void register() {

        //Commands Registrieren!

        new SetWarpCommand(this);
        new FlyCommand(this);
        new ChatClear(this);
        new LobbyCommand(this);
        new HubCommand(this);
        new WerbungCommand(this);
        new ECCommand(this);
        new GamemodeCommand(this);

        //Listeners Registrieren!

        new PlayerJoinQuitEventListener(this);
        new InventoryClickEventListener(this);
        new Events(this);
        new PlayerInteractEventListener(this);
        new ChatPrefix(this);
        new CommandsBlocker(this);
        new PlayerChatListener(this);

        //Utils Registrieren!

        teleportUtils = new TeleportUtils(this);
        scoreBoardManager = new ScoreBoardManager(this);
    }

    //Plugin Deaktiviert!

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§cDas Lobby-System wurde gestoppt!");
        Bukkit.getConsoleSender().sendMessage("§7========================================================");
    }

    public static TeleportUtils getTeleportUtils() {
        return teleportUtils;
    }

    //Prefix!

    public static String getPrefix() {
        return "§7[§6Lobby§7] §r";
    }

    public static String getNoPlayer() {
        return getPrefix() + "§cDu bist kein Spieler!";
    }

    public static String getNoPerm() {
        return getPrefix() + "§cDazu hast du keine Rechte!";
    }

    public static String use() {
        return getPrefix() + "§cBitte Benutze: §6";
    }

    public static String world() {
        return "world";
    }

}
