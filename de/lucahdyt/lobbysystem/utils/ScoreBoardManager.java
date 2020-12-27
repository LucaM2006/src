package de.lucahdyt.lobbysystem.utils;

import de.lucahdyt.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreBoardManager {

    private static Integer animationCount;

    private String[] animation = new String[]{
            "§6Schlumpfkopf.de", "§6Schlumpfkopf.de", "§eSchlumpfkopf.de", "§eSchlumpfkopf.de", "§6Schlumpfkopf.de", "§6Schlumpfkopf.de", "§eSchlumpfkopf.de", "§eSchlumpfkopf.de"
    };

    public ScoreBoardManager(LobbySystem lobbySystem) {

    }

    public void setScoreBoard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("ScoreBoard", "dummy");

            Team owner = board.registerNewTeam("00100Owner");
            Team leitung = board.registerNewTeam("00200Leitung");
            Team admin = board.registerNewTeam("00300Admin");
            Team mod = board.registerNewTeam("00400Mod");
            Team dev = board.registerNewTeam("00500Dev");
            Team sup = board.registerNewTeam("00600Sup");
            Team builder = board.registerNewTeam("00700Builder");
            Team tmod = board.registerNewTeam("00800T-Mod");
            Team tdev = board.registerNewTeam("00900T-Dev");
            Team tsup = board.registerNewTeam("001000T-Sup");
            Team tbuilder = board.registerNewTeam("001100T-Builder");
            Team kopf = board.registerNewTeam("001200Kopf");
            Team vip = board.registerNewTeam("001300Vip");
            Team youtuber = board.registerNewTeam("001400YouTuber");
            Team spieler = board.registerNewTeam("001500Spieler");

            Team spieleranzahl = board.registerNewTeam("Spieler");

            owner.setPrefix("§4[O] §8| §4");
            leitung.setPrefix("§4[L] §8| §4");
            admin.setPrefix("§4[A] §8| §4");
            mod.setPrefix("§9[M] §8| §9");
            dev.setPrefix("§3[Dev] §8| §3");
            sup.setPrefix("§6[Sup] §8| §6");
            builder.setPrefix("§2[B] §8| §2");
            tmod.setPrefix("§9[T-M] §8| §9");
            tdev.setPrefix("§3[T-Dev] §8| §3");
            tsup.setPrefix("§6[T-Sup] §8| §6");
            tbuilder.setPrefix("§2[T-B] §8| §2");
            kopf.setPrefix("§c[K] §8| §c");
            vip.setPrefix("§6[V] §8| §6");
            youtuber.setPrefix("§5[YT] §8| §5");
            spieler.setPrefix("§7[S] §8| §7");

            spieleranzahl.setPrefix("§6" + Bukkit.getOnlinePlayers().size());
            spieleranzahl.addEntry("§6");

            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(animation[animationCount]);

            obj.getScore("§aHallo: §6" + player.getName() + "§6  ").setScore(9);
            obj.getScore("§1").setScore(8);
            obj.getScore("§aDein Rang:").setScore(7);

            if (player.hasPermission("lobby.owner")) {
                obj.getScore("§4Owner").setScore(6);
            } else if (player.hasPermission("lobby.leitung")) {
                obj.getScore("§4Leitung").setScore(6);
            } else if (player.hasPermission("lobby.admin")) {
                obj.getScore("§4Admin").setScore(6);
            } else if (player.hasPermission("lobby.mod")) {
                obj.getScore("§9Mod").setScore(6);
            } else if (player.hasPermission("lobby.dev")) {
                obj.getScore("§3Dev").setScore(6);
            } else if (player.hasPermission("lobby.sup")) {
                obj.getScore("§6Sup").setScore(6);
            } else if (player.hasPermission("lobby.builder")) {
                obj.getScore("§2Builder").setScore(6);
            } else if (player.hasPermission("lobby.t-mod")) {
                obj.getScore("§9T-Mod").setScore(6);
            } else if (player.hasPermission("lobby.t-dev")) {
                obj.getScore("§3T-Dev").setScore(6);
            } else if (player.hasPermission("lobby.t-sup")) {
                obj.getScore("§6T-Sup").setScore(6);
            } else if (player.hasPermission("lobby.t-builder")) {
                obj.getScore("§2T-Builder").setScore(6);
            } else if (player.hasPermission("lobby.kopf")) {
                obj.getScore("§cKopf").setScore(6);
            } else if (player.hasPermission("lobby.vip")) {
                obj.getScore("§6Vip").setScore(6);
            } else if (player.hasPermission("lobby.youtuber")) {
                obj.getScore("§5YouTuber").setScore(6);
            } else
                obj.getScore("§7Spieler").setScore(6);

            obj.getScore("§3").setScore(5);
            obj.getScore("§aSpieler:").setScore(4);
            obj.getScore("§6").setScore(3);
            obj.getScore("§4").setScore(2);
            obj.getScore("§aWebsite").setScore(1);
            obj.getScore("§awww.schlumpfkopf.de  ").setScore(0);


            Bukkit.getOnlinePlayers().forEach(p -> {
                if (p.hasPermission("lobby.owner")) {
                    owner.addEntry(p.getName());
                } else if (p.hasPermission("lobby.leitung")) {
                    leitung.addEntry(p.getName());
                } else if (p.hasPermission("lobby.admin")) {
                    admin.addEntry(p.getName());
                } else if (p.hasPermission("lobby.mod")) {
                    mod.addEntry(p.getName());
                } else if (p.hasPermission("lobby.dev")) {
                    dev.addEntry(p.getName());
                } else if (p.hasPermission("lobby.sup")) {
                    sup.addEntry(p.getName());
                } else if (p.hasPermission("lobby.builder")) {
                    builder.addEntry(p.getName());
                } else if (p.hasPermission("lobby.t-mod")) {
                    tmod.addEntry(p.getName());
                } else if (p.hasPermission("lobby.t-dev")) {
                    tdev.addEntry(p.getName());
                } else if (p.hasPermission("lobby.t-sup")) {
                    tsup.addEntry(p.getName());
                } else if (p.hasPermission("lobby.t-builder")) {
                    tbuilder.addEntry(p.getName());
                } else if (p.hasPermission("lobby.kopf")) {
                    kopf.addEntry(p.getName());
                } else if (p.hasPermission("lobby.vip")) {
                    vip.addEntry(p.getName());
                } else if (p.hasPermission("lobby.youtuber")) {
                    youtuber.addEntry(p.getName());
                } else
                    spieler.addEntry(p.getName());
            });

            player.setScoreboard(board);

        }

    }

    public void startAnimation() {
        animationCount = 0;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player1 -> {

                    if (player1.getScoreboard() == null)
                        setScoreBoard();

                    player1.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationCount]);

                });

                animationCount++;

                if (animationCount == animation.length)
                    animationCount = 0;

            }
        }, 0, 5);
    }

    public void update1() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getScoreboard().getTeam("Spieler").setPrefix("§6" + (Bukkit.getOnlinePlayers().size() - 1));
        }
    }

    public void update2() {
        for (Player p : Bukkit.getOnlinePlayers())
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationCount]);
    }

}
