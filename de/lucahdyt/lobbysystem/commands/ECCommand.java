package de.lucahdyt.lobbysystem.commands;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ECCommand implements CommandExecutor {

    public static ArrayList<UUID> enderchest = new ArrayList<>();

    public ECCommand(LobbySystem lobbySystem) {
        Bukkit.getPluginCommand("ec").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 0) {

                p.openInventory(p.getEnderChest());
                p.sendMessage(LobbySystem.getPrefix() + "§aDu hast deine EC geöffnet!");

            } else if (args.length == 1) {
                if (p.hasPermission("lobby.ec.*")) {

                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {

                        p.openInventory(target.getEnderChest());
                        p.sendMessage(LobbySystem.getPrefix() + "§aDu schaust in die EC von §6" + target.getName() + "§a rein!");
                        enderchest.contains(p.getUniqueId());

                    } else {
                        p.sendMessage(LobbySystem.getPrefix() + "§cDer spieler §6" + args[0] + "§c ist nicht auf dem Server!");
                    }

                } else {
                    p.sendMessage(LobbySystem.getNoPerm());
                }
            } else {
                p.sendMessage(LobbySystem.use() + "/ec | /ec <Spieler>");
            }

        } else {
            sender.sendMessage(LobbySystem.getNoPlayer());
        }

        return false;
    }
}
