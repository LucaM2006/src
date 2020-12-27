package de.lucahdyt.lobbysystem.commands;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    public GamemodeCommand(LobbySystem lobbySystem) {
        Bukkit.getPluginCommand("gamemode").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("3")) {

                    switch (Integer.parseInt(args[0])) {

                        case 0:

                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu bist nun im §6ÜberlebensModus§a!");

                            break;

                        case 1:

                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu bist nun im §6CreativeModus§a!");

                            break;

                        case 2:

                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu bist nun im §6AbenteuerModus§a!");

                            break;

                        case 3:

                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu bist nun im §6ZuschauerModus§a!");

                            break;
                    }

                } else {
                    p.sendMessage(LobbySystem.use() + "/gm <0/1/2/3> | /gm <0/1/2/3> <Spieler>");
                }

            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {

                    switch (Integer.parseInt(args[0])) {

                        case 0:

                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(LobbySystem.getPrefix() + "§aDer Spieler §6" + p.getName() + "§a hat dich in denn §6ÜberlebensModus§a gesetzt!");
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu hast §6" + target.getName() + " §ain denn §6ÜberlebensModus§a gesetzt!");

                            break;

                        case 1:

                            target.setGameMode(GameMode.CREATIVE);
                            target.sendMessage(LobbySystem.getPrefix() + "§aDer Spieler §6" + p.getName() + "§a hat dich in denn §6CreativeModus§a gesetzt!");
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu hast §6" + target.getName() + "§a in denn §6CreativeModus§a gesetzt!");

                            break;

                        case 2:

                            target.setGameMode(GameMode.ADVENTURE);
                            target.sendMessage(LobbySystem.getPrefix() + "§aDer Spieler §6" + p.getName() + "§a hat dich in denn §6AbenteuerModus§a gesetzt!");
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu hast §6" + target.getName() + "§a in denn §6AbenteuerModus§a gesetzt!");

                            break;

                        case 3:

                            target.setGameMode(GameMode.SPECTATOR);
                            target.sendMessage(LobbySystem.getPrefix() + "§aDer Spieler §6" + p.getName() + "§a hat dich in denn §6ZuschauerModus§a gesetzt!");
                            p.sendMessage(LobbySystem.getPrefix() + "§aDu hast §6" + target.getName() + "§a in denn §6ZuschauerModus§a gesetzt!");

                            break;
                    }


                } else {
                    p.sendMessage(LobbySystem.getPrefix() + "§cDer Spieler §6" + args[0] + "§c ist nicht Online!");
                }

            } else {
                p.sendMessage(LobbySystem.use() + "/gm <0/1/2/3> | /gm <0/1/2/3> <Spieler>");
            }

        } else {
            sender.sendMessage(LobbySystem.getNoPlayer());
        }

        return false;
    }
}
