package de.lucahdyt.lobbysystem.listeners;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatPrefix implements Listener {

    public ChatPrefix(LobbySystem lobbySystem) {
        Bukkit.getPluginManager().registerEvents(this, lobbySystem);
    }

    @EventHandler
    public void onChatPrefix(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        if (p.hasPermission("lobby.owner")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §4" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.leitung")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §4" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.admin")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §4" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.mod")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §9" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.dev")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §3" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.sup")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §6" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.builder")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §2" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.t-mod")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §9" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.t-dev")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §3" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.t-sup")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §6" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.t-builder")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §2" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.kopf")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §c" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.vip")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §6" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.youtuber")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §5" + msg.replace("&", "§"));

        } else if (p.hasPermission("lobby.spieler")) {
            e.setFormat(PrefixHandler.getChatPrefix(p) + p.getName() + " §8» §7" + msg);
        }
    }
}
