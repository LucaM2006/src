package de.lucahdyt.lobbysystem.commands;

import de.lucahdyt.lobbysystem.LobbySystem;
import de.lucahdyt.lobbysystem.utils.LobbyInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {

    public LobbyCommand(LobbySystem lobbySystem) {
        Bukkit.getPluginCommand("lobby").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (!p.getWorld().getName().equals(LobbySystem.world())) {
                p.teleport(Bukkit.getWorld(LobbySystem.world()).getSpawnLocation());
                p.sendMessage(LobbySystem.getPrefix() + "§aDu bist nun auf der Lobby");
                new LobbyInventory(p).setLobbyInventory();

            } else {
                p.sendMessage(LobbySystem.getPrefix() + "§cDu bist bereits auf der Lobby!");
            }

        } else {
            sender.sendMessage(LobbySystem.getNoPlayer());
        }

        return false;
    }
}
