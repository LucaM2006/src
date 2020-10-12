// This Class was Created by LucaHD

package de.luca.SpawnHealSystem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("§aDer Spieler §6" + event.getPlayer().getName() + "§a hat den Server betreten.");
    }

}