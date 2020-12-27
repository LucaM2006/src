package de.lucahdyt.lobbysystem.commands;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WerbungCommand implements CommandExecutor {

    public WerbungCommand(LobbySystem lobbySystem) {
        Bukkit.getPluginCommand("lobbysystem").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 0) {

                p.sendMessage("");
                p.sendMessage("§a§l§m==================== §6§lLobbySystem §a§l§m====================");
                p.sendMessage("");
                p.sendMessage("§a§lHier seht ihr ein Par Infos über das §6§lLobbySystem§a§l!");
                p.sendMessage("");
                p.sendMessage("§a§lDas §6§lLobbySystem §a§list sehr Einfach zu verstehen!");
                p.sendMessage("");
                p.sendMessage("§a§lDas §6§lLobbySystem §a§lhat folgende Funktionen:");
                p.sendMessage("");
                p.sendMessage("§6§lTeleporter, Spieler Verstecken, Gadgets, Dein Profil§a§l!");
                p.sendMessage("");
                p.sendMessage("§a§lHier könnt ihr den Coder Abonnieren:");
                p.sendMessage("");
                p.sendMessage("§a§lhttps://www.youtube.com/channel/UC5rZpmqFTtYom0-CYCublJg?sub_confirmation=1");
                p.sendMessage("");
                p.sendMessage("§a§lDas §6§lLobbySystem §a§lwurde Programmiert von: LucaHD");
                p.sendMessage("");
                p.sendMessage("§a§l§m==================== §6§lLobbySystem §a§l§m====================");

            } else {
                p.sendMessage(LobbySystem.use() + "/LobbySystem");
            }

        } else {
            sender.sendMessage(LobbySystem.getNoPlayer());
        }

        return false;
    }
}
