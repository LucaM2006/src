// This class was created by LucaHD

package de.luca.customreload.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomReload extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onUnknown(PlayerCommandPreprocessEvent event) {
        if (!(event.isCancelled())) {
            Player p = event.getPlayer();
            String msg = event.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if (topic == null) {
                p.sendMessage("§cDer Befehl §4[" + msg + "] §cExistiert nicht!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void Reload(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        String command = event.getMessage();
        if (command.equalsIgnoreCase("/rl") || command.equalsIgnoreCase("/reload")) {
            if (p.isOp()) {
                event.setCancelled(true);
                Bukkit.broadcastMessage("§c§lSERVER RELOADED!");
                Bukkit.broadcastMessage("");
                Bukkit.reload();
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§a§lSERVER RELOAD ABGESCHLOSSEN!");
            }
        }
    }

}
