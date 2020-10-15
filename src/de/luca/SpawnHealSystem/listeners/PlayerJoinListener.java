import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("abcd", "abcd");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§a§lWillkommen auf DeinServer.de");
        objective.getScore("§6----------------------------").setScore(6);
        objective.getScore("§aHallo §6" + player.getName() + "§a!").setScore(5);
        objective.getScore("§e----------------------------").setScore(4);
        objective.getScore("§aViel Spaß auf dem Server!").setScore(3);
        objective.getScore("§6---------------------------- ").setScore(2);
        objective.getScore("§aScoreBoard wurde gecodet von: Luca").setScore(1);
        objective.getScore("§e---------------------------- ").setScore(0);
        player.setScoreboard(board);
    }
}
