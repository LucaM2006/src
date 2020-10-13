package de.luca.SpawnHealSystem.commands;

import de.luca.SpawnHealSystem.main.Main;
import de.luca.SpawnHealSystem.util.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor{


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)){
			sender.sendMessage(Main.getPrefix() + "§cDu bist kein Spieler!");
			return true;
		}
		final Player player = ((Player) sender);

		if (args.length == 0){

			}else {
				player.sendMessage(Main.getPrefix() + "§cDer §6Spawn §cwurde noch nicht gesetzt." +
						"§cBitte setze den §6Spawn§c!");
			}

		}else {
			player.sendMessage(Main.getPrefix() + "§cBitte benutze §6/spawn");
		}

		return true;
	}
}
