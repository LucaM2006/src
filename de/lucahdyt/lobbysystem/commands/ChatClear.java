package de.lucahdyt.lobbysystem.commands;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClear implements CommandExecutor {

    public ChatClear(LobbySystem lobbySystem) {
        Bukkit.getPluginCommand("ChatClear").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lobby.chatclear")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    for (int i = 0; i < 250; i++)
                        all.sendMessage(" ");
                    all.sendMessage(LobbySystem.getPrefix() + "§7Der Chat wurde von §4" + p.getName() + " §7gelöscht");
                    Bukkit.getConsoleSender().sendMessage(LobbySystem.getPrefix() + "§7Der Chat wurde von §4" + p.getName() + " §7gelöscht");
                }
            } else {
                p.sendMessage(LobbySystem.getNoPerm());
            }
        } else {
            sender.sendMessage(LobbySystem.getNoPlayer());
        }
        return false;
    }
}
