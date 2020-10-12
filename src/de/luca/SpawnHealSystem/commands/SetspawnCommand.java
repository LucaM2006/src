package de.luca.SpawnHealSystem.commands;

import de.luca.SpawnHealSystem.util.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.luca.SpawnHealSystem.main.Main;

public class SetspawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Main.getPrefix()+ "§cDu bist kein Spieler!");
			return true;
		}
		
		
		final Player player = (Player) sender;
		
		if(!player.hasPermission("tutorial.setspawn")) {
			player.sendMessage(Main.getPrefix() + "§cDazu hast du keine Rechte!");
			return true;
			
		}
		if (args.length == 0){
			ConfigUtils.config.set("Spawn",player.getLocation());
			ConfigUtils.savedata();
			player.sendMessage(Main.getPrefix() + "§aDu hast den §6Spawn §aerfolgreich gesetzt.");
		}else {

			player.sendMessage(Main.getPrefix()+ "§cBitte benutze §6/setspawn");
		}
		
		
		
		return true;
	}

}
