// This Class was Created by LucaHD

package de.luca.SpawnHealSystem.util;

import de.luca.SpawnHealSystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class CountDownManager {
    static int TaskID;
    static int countdown = 3;
    static Location location = (Location) ConfigUtils.config.get("Spawn");


    public void StartCount(Player player) {
        if (Bukkit.getScheduler().isCurrentlyRunning(TaskID) || Bukkit.getScheduler().isQueued(TaskID)) {
            player.sendMessage(Main.getPrefix() + "§cDu wirst doch gerade schon Teleportiert.");
            return;
        }


        TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (countdown > 0) {
                player.sendTitle("§aTeleportiere", "§4" + countdown);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                countdown--;
            } else {
                finish(player);
                Bukkit.getScheduler().cancelTask(TaskID);
            }


        }, 0, 20);

    }

    private void finish(Player player) {
        player.teleport(location);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10F, 10F);
        countdown = 3;

    }
}
