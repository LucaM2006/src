package de.lucahdyt.lobbysystem.commands;

import de.lucahdyt.lobbysystem.LobbySystem;
import de.lucahdyt.lobbysystem.utils.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    public SetWarpCommand(LobbySystem lobbySystem) {
        Bukkit.getPluginCommand("setwarp").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() || sender.hasPermission("lobby.setup")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 3) {
                    String name = args[0];
                    Material material = null;
                    int slot = 0;
                    try {
                        material = Material.valueOf(args[1].toUpperCase());
                    } catch (Exception e1) {
                        p.sendMessage(LobbySystem.getPrefix() + "§c" + args[1].toUpperCase() + "§c ist kein gültiges Material!");
                        return true;
                    }
                    try {
                        slot = Integer.parseInt(args[2]);
                    } catch (Exception e1) {
                        p.sendMessage(LobbySystem.getPrefix() + "§c<Slot> muss eine Zahl sein!");
                        return true;
                    }
                    TeleportUtils.save(name, material, slot, p.getLocation());
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 1.0F);
                    p.sendMessage(LobbySystem.getPrefix() + "§aDu hast den §6Warp §agesetzt!");
                } else {
                    p.sendMessage(LobbySystem.use() + "/setwarp <Name> <Material> <Slot>");
                }
            } else {
                sender.sendMessage(LobbySystem.getNoPlayer());
            }
        } else {
            sender.sendMessage(LobbySystem.getNoPerm());
        }
        return true;
    }
}
