package de.lucahdyt.lobbysystem.listeners;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    public PlayerChatListener(LobbySystem lobbySystem) {
        Bukkit.getPluginManager().registerEvents(this, lobbySystem);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("lobby.chat.vip")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
            p.sendMessage(LobbySystem.getPrefix() + "§c§lNur VIP Spieler können in diesem Chat Schreiben!");
        }
    }

}
