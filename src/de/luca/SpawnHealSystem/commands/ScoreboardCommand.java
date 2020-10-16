import de.luca.SpawnHealSystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("sb.reload")) {
                if(args.length == 1) {
                    player.sendMessage(Main.getPrefix() + ("§eScoreBoard wird neu geladen..."));
                    try {
                        reload(player);
                        player.sendMessage(Main.getPrefix() + ("§aScoreBoard wurde neu geladen!"));
                    }catch (Exception e){
                        player.sendMessage(Main.getPrefix() + ("§cFehler: " + e.getMessage()));
                    }

                }else {
                    player.sendMessage(Main.getPrefix() + ("§cBitte benutze: §6/sb reload"));
                }
            }else {
                player.sendMessage(Main.getPrefix() + ("§cDazu hast du keine Rechte!"));
            }
        } else {
            sender.sendMessage(Main.getPrefix() + ("§cDu bist kein Spieler"));
        }

        return true;
    }

    void reload(Player player){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("abcd", "abcd");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6§lWillkommen auf DeinServer.de");
        objective.getScore("§6§l--------------------------").setScore(6);
        objective.getScore("§a§lHallo §6§l" + player.getName() + "§a§l!").setScore(5);
        objective.getScore("§e§l----------------------").setScore(4);
        objective.getScore("§a§lViel Spaß §6§l" + player.getName() + "§a§l!").setScore(3);
        objective.getScore("§6§l-------------------------").setScore(2);
        objective.getScore("§a§lgecodet von: §6§lLucaHD").setScore(1);
        objective.getScore("§e§l--------------------------------").setScore(0);
        player.setScoreboard(board);
    }
}
