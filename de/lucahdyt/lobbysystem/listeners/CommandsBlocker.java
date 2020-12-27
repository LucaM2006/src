package de.lucahdyt.lobbysystem.listeners;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CommandsBlocker implements Listener {

    public CommandsBlocker(LobbySystem lobbySystem) {
        Bukkit.getPluginManager().registerEvents(this, lobbySystem);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().toLowerCase().startsWith("/pl")
                || e.getMessage().toLowerCase().startsWith("/plugins")
                || e.getMessage().toLowerCase().startsWith("/help")
                || e.getMessage().toLowerCase().startsWith("/version")
                || e.getMessage().toLowerCase().startsWith("/ver")
                || e.getMessage().toLowerCase().startsWith("/bungee")
                || e.getMessage().toLowerCase().startsWith("/bukkit")
                || e.getMessage().toLowerCase().startsWith("/?")
                || e.getMessage().toLowerCase().startsWith("/Bukkit:help")
                || e.getMessage().toLowerCase().startsWith("/Bukkit:?")
                || e.getMessage().toLowerCase().startsWith("/Bukkit:hilfe")
                || e.getMessage().toLowerCase().startsWith("/Bukkit:about")
                || e.getMessage().toLowerCase().startsWith("/about")
        ) {

            if (!p.isOp()) {
                p.sendMessage("§c§lUnsere Plugins sind Privat.");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onUnknown(PlayerCommandPreprocessEvent e) {
        if (!(e.isCancelled())) {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if (topic == null) {
                p.sendMessage("§c§lDer Befehl: §6§l" + msg + "§c§l existiert nicht!");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onReload(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String cmd = e.getMessage();

        if (cmd.equalsIgnoreCase("/rl") || cmd.equalsIgnoreCase("/reload")) {

            if (p.isOp()) {

                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§c§lDer Server wird Reloaded!");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§c§lBitte bewege dich nicht!");
                Bukkit.broadcastMessage("§c§lDa der Server evtl. Abstürzen könnte!");
                Bukkit.reload();
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§a§lServer Reload erfolgreich Abgeschlossen!");
                Bukkit.broadcastMessage("");
                e.setCancelled(true);

            }
        }
    }

}
