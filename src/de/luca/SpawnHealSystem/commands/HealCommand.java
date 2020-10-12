package de.luca.SpawnHealSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("tutorial.heal")) {
				if(args.length == 0) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage("§aDu wurdest geheilt!");
				} else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null) {
						target.setHealth(20);
						target.setFoodLevel(20);
						target.sendMessage("§aDu wurdest von §6" + player.getName() + " §ageheilt!");
						player.sendMessage("§aDu hast den Spieler §6" + target.getName() + " §ageheilt.");
					} else
						player.sendMessage("§cDer Spieler §6" + args[0] + " §cist nicht auf dem Server!");
				} else
					player.sendMessage("§cBitte benutze §6/heal <Spieler>");
			} else
				player.sendMessage("§cDazu hast du keine Rechte!");
		} else
			sender.sendMessage("§cDu bist kein Spieler!");
		
		return false;
	}

}
