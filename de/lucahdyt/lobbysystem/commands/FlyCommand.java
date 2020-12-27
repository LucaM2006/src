package de.lucahdyt.lobbysystem.commands;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    public FlyCommand(LobbySystem lobbySystem) {
        Bukkit.getPluginCommand("Fly").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("lobby.fly")) {
                if (args.length == 0) {
                    if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.sendMessage(LobbySystem.getPrefix() + "§cDu kannst nun nicht mehr Fliegen!");
                    } else {
                        p.setAllowFlight(true);
                        p.sendMessage(LobbySystem.getPrefix() + "§aDu kannst nun Fliegen!");
                    }
                } else {
                    sender.sendMessage(LobbySystem.use() + "/Fly");
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
