package de.lucahdyt.lobbysystem.listeners;

import org.bukkit.entity.Player;

public class PrefixHandler {
    public static String getChatPrefix(Player p) {
        if (p.hasPermission("lobby.owner")) {
            return "§4[Owner] §8| §4";
        } else if (p.hasPermission("lobby.leitung")) {
            return "§4[Leitung] §8| §4";
        } else if (p.hasPermission("lobby.admin")) {
            return "§4[Admin] §8| §4";
        } else if (p.hasPermission("lobby.mod")) {
            return "§9[Mod] §8| §9";
        } else if (p.hasPermission("lobby.dev")) {
            return "§3[Dev] §8| §3";
        } else if (p.hasPermission("lobby.sup")) {
            return "§6[Sup] §8| §6";
        } else if (p.hasPermission("lobby.builder")) {
            return "§2[Builder] §8| §2";
        } else if (p.hasPermission("lobby.t-mod")) {
            return "§9[Test-Mod] §8| §9";
        } else if (p.hasPermission("lobby.t-dev")) {
            return "§3[Test-Dev] §8| §3";
        } else if (p.hasPermission("lobby.t-sup")) {
            return "§6[Test-Sup] §8| §6";
        } else if (p.hasPermission("lobby.t-builder")) {
            return "§2[Test-Builder] §8| §2";
        } else if (p.hasPermission("lobby.kopf")) {
            return "§c[Kopf] §8| §c";
        } else if (p.hasPermission("lobby.vip")) {
            return "§6[VIP] §8| §6";
        } else if (p.hasPermission("lobby.youtuber")) {
            return "§5[YouTuber] §8| §5";
        } else
            return "§7[Spieler] §8| §7";
    }
}
