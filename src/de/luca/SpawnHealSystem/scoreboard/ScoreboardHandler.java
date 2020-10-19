package de.luca.SpawnHealSystem.scoreboard;

import de.luca.SpawnHealSystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardHandler {

    private static Integer animationCount;

    private String[] animation = new String[]{
            "§6§lWillkommen", "§e§lWillkommen", "§e§lWillkommen", "§6§lWillkommen"
    };

    public void setScoreboard(final Player player) {
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("main-content", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(animation[animationCount]);

        objective.getScore("§a§lNOCH IN ARBEIT").setScore(5);
        objective.getScore("§6§lNOCH IN ARBEIT").setScore(3);
        objective.getScore("§e§lNOCH IN ARBEIT").setScore(2);
        objective.getScore("§a§lNOCH IN ARBEIT").setScore(1);

        player.setScoreboard(scoreboard);

    }

    public void startAnimation() {
        animationCount = 0;
        Bukkit.getScheduler().runTaskTimer(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(current -> {

                    if (current.getScoreboard() == null)
                        setScoreboard(current);

                    current.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationCount]);

                });

                animationCount++;

                if (animationCount == animation.length)
                    animationCount = 0;


            }
        }, 0, 5);
    }

}
