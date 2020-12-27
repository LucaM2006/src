package de.lucahdyt.lobbysystem.listeners;

import de.lucahdyt.lobbysystem.LobbySystem;
import de.lucahdyt.lobbysystem.utils.LobbyInventory;
import de.lucahdyt.lobbysystem.utils.ScoreBoardManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitEventListener implements Listener {

    private LobbySystem lobbySystem;

    public PlayerJoinQuitEventListener(LobbySystem lobbySystem) {
        this.lobbySystem = lobbySystem;
        Bukkit.getPluginManager().registerEvents(this, lobbySystem);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new ScoreBoardManager(lobbySystem).setScoreBoard();
        for (int i = 0; i < 250; ++i) {
            p.sendMessage("");
        }
        p.sendMessage("§aDu bist auf §6Schlumpfkopf.de\n" +
                "§aViel Spaß auf §6Schlumpfkopf.de");
        e.setJoinMessage("§8[§a+§8] §7" + p.getName());
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        new LobbyInventory(e.getPlayer()).setLobbyInventory();
        e.getPlayer().setHealth(20.0D);
        e.getPlayer().setFoodLevel(20);
        e.getPlayer().setLevel(0);
        e.getPlayer().setFireTicks(0);
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e) {
        new ScoreBoardManager(lobbySystem).update1();
        e.setQuitMessage(null);
    }
}
