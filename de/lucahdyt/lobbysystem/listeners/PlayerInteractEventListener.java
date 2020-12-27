package de.lucahdyt.lobbysystem.listeners;

import de.lucahdyt.lobbysystem.LobbySystem;
import de.lucahdyt.lobbysystem.utils.ItemBuilder;
import de.lucahdyt.lobbysystem.utils.TeleportUtils;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerInteractEventListener implements Listener {

    private int taskID1;
    private int taskID2;

    public Inventory gadgetsInventory;
    public Inventory profileInventory;
    public Inventory adminInventory;

    public LobbySystem lobbySystem;

    public List<Player> hide;

    public PlayerInteractEventListener(LobbySystem lobbySystem) {

        this.lobbySystem = lobbySystem;

        Bukkit.getPluginManager().registerEvents(this, lobbySystem);

        hide = new ArrayList<>();

        adminInventory = Bukkit.createInventory(null, 9, "§c§lAdminItems");
        adminInventory.setItem(2, new ItemBuilder(Material.FEATHER).setName("§c§lFly").build());
        adminInventory.setItem(4, new ItemBuilder(Material.BEACON).setName("§c§lReloadServer").build());
        adminInventory.setItem(6, new ItemBuilder(Material.REDSTONE_BLOCK).setName("§c§lRestartServer").build());
        adminInventory.setItem(8, new ItemBuilder(Material.BARRIER).setName("§c§lSchließen").build());

        gadgetsInventory = Bukkit.createInventory(null, 9, "§6§lGadgets");
        gadgetsInventory.setItem(0, new ItemBuilder(Material.ENDER_PEARL).setName("§b§lEnderperle").build());
        gadgetsInventory.setItem(1, new ItemBuilder(Material.FIREWORK).setName("§b§lFeuerWerk").build());
        gadgetsInventory.setItem(8, new ItemBuilder(Material.BARRIER).setName("§c§lKein Gadget ausgewählt").build());

        profileInventory = Bukkit.createInventory(null, 9, "§6§lDein Profil");
        profileInventory.setItem(2, new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§b§lHüte").build());
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (e.getItem() != null && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().getDisplayName() != null) {

            //Spieler Verstecken!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§lSpieler verstecken")) {
                this.hide.add(e.getPlayer());
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (e.getPlayer() != p) {
                        e.getPlayer().hidePlayer(p);
                        e.getPlayer().playEffect(p.getLocation(), Effect.EXPLOSION, 0);
                    }
                });
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 3.0F, 1.0F);
                e.getPlayer().getInventory().setItem(2, (new ItemBuilder(Material.BLAZE_ROD)).setName("§6§lSpieler anzeigen").build());
                e.getPlayer().updateInventory();
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpieler anzeigen")) {
                this.hide.remove(e.getPlayer());
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (e.getPlayer() != p) {
                        e.getPlayer().showPlayer(p);
                        e.getPlayer().playEffect(p.getLocation(), Effect.EXPLOSION, 0);
                    }
                });
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 3.0F, 1.0F);
                e.getPlayer().getInventory().setItem(2, (new ItemBuilder(Material.STICK)).setName("§7§lSpieler verstecken").build());
                e.getPlayer().updateInventory();

                //Dein Profil!
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDein Profil")) {
                e.getPlayer().openInventory(profileInventory);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_STICKS, 3.0F, 1.0F);
            }

            //Teleporter!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTeleporter")) {
                Inventory inv = Bukkit.createInventory(null, 9 * 5, "§6§lTeleporter");

                List<String> list = TeleportUtils.getWarps();
                for (String s : list) {
                    try {
                        String name = s.split(":")[0];
                        Material material = Material.valueOf(s.split(":")[1].toUpperCase());
                        int slot = Integer.parseInt(s.split(":")[2]);
                        Location location = new Location(Bukkit.getWorld(s.split(":")[3]), Double.valueOf(s.split(":")[4]).doubleValue(), Double.valueOf(s.split(":")[5]).doubleValue(),
                                Double.valueOf(s.split(":")[6]).doubleValue(), Float.valueOf(s.split(":")[7]).floatValue(), Float.valueOf(s.split(":")[8]).floatValue());
                        TeleportUtils.locationHashMap.put(name, location);
                        inv.setItem(slot, new ItemBuilder(material).setName(name).build());
                    } catch (Exception exception) {
                    }
                }
                e.getPlayer().openInventory(inv);
            }

            //Gadgets!
            if (e.getItem() != null && e.getItem().getType() == Material.CHEST) {
                e.getPlayer().openInventory(gadgetsInventory);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CHEST_OPEN, 3.0F, 1.0F);
                e.setCancelled(true);
            }

            //Dein Profil!
            if (e.getItem() != null && e.getItem().getType() == Material.SKULL_ITEM) {
                e.getPlayer().openInventory(profileInventory);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CHEST_OPEN, 3.0F, 1.0F);
                e.setCancelled(true);
            }

            //Gadgets: EnderPerle!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lEnderperle")) {
                if (e.getItem() != null && e.getItem().getType() == Material.ENDER_PEARL) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        final Player p = e.getPlayer();
                        e.setCancelled(true);
                        p.getInventory().setItem(4, new ItemBuilder(Material.FIREWORK_CHARGE).setName("§7§lEnderperle").build());
                        p.updateInventory();
                        EnderPearl enderPearl = (EnderPearl) p.launchProjectile(EnderPearl.class);
                        enderPearl.setPassenger((Entity) p);
                        Bukkit.getScheduler().runTaskLater(lobbySystem, new Runnable() {
                            public void run() {
                                p.getInventory().setItem(4, new ItemBuilder(Material.ENDER_PEARL).setName("§b§lEnderperle").build());
                                p.updateInventory();
                            }
                        }, 60L);
                    }
                }
            }

            //Gadgets: Feuerwerk!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lFeuerWerk")) {
                if (e.getItem() != null && e.getItem().getType() == Material.FIREWORK) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        final Player p = e.getPlayer();
                        e.setCancelled(true);
                        p.getInventory().setItem(4, new ItemBuilder(Material.FIREWORK_CHARGE).setName("§7§lFeuerWerk").build());
                        p.updateInventory();
                        Location location = p.getLocation();
                        if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
                            location = e.getClickedBlock().getRelative(BlockFace.UP).getLocation();
                        FireworkMeta fireworkMeta = (FireworkMeta) e.getItem().getItemMeta();
                        fireworkMeta.setPower(1);
                        fireworkMeta.addEffect(FireworkEffect.builder().withFade(Color.LIME).withColor(Color.BLUE).trail(true).build());
                        Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
                        firework.setFireworkMeta(fireworkMeta);
                        Bukkit.getScheduler().runTaskLater(lobbySystem, new Runnable() {
                            public void run() {
                                p.getInventory().setItem(4, new ItemBuilder(Material.FIREWORK).setName("§b§lFeuerWerk").build());
                                p.updateInventory();
                            }
                        }, 60L);
                    }
                }
            }

            //AdminItems!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lAdminItems")) {
                if (e.getItem() != null && e.getItem().getType() == Material.REDSTONE_TORCH_ON) {
                    e.getPlayer().openInventory(adminInventory);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CHEST_OPEN, 3.0F, 1.0F);
                    e.setCancelled(true);
                }
            }

            //AdminItems: Fly!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lFly")) {
                if (e.getItem() != null && e.getItem().getType() == Material.FEATHER) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Player p = e.getPlayer();
                        e.setCancelled(true);
                        p.getInventory().setItem(6, new ItemBuilder(Material.FEATHER).setName("§c§lFly").build());
                        p.closeInventory();
                        p.updateInventory();
                        if (!p.getAllowFlight()) {
                            p.setAllowFlight(true);
                            p.setFlying(true);
                            p.sendMessage("§7[§c§lAdminItems§7] §a§lDu kannst nun Fliegen!");
                        } else {
                            p.setAllowFlight(false);
                            p.setFlying(false);
                            p.sendMessage("§7[§c§lAdminItems§7] §c§lDu kannst nun nicht mehr Fliegen!");
                        }
                    }
                }
            }

            //AdminItems: ReloadServer!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lReloadServer")) {
                if (e.getItem() != null && e.getItem().getType() == Material.BEACON) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Player p = e.getPlayer();
                        p.getInventory().setItem(6, new ItemBuilder(Material.BEACON).setName("§c§lReloadServer").build());
                        taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.instance, new Runnable() {

                            int count = 10;

                            @Override
                            public void run() {
                                if (count < 1) {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        all.kickPlayer("§c§lDer Server wird neu geladen!");
                                    }
                                    Bukkit.reload();
                                    Bukkit.getScheduler().cancelTask(taskID1);
                                } else if (count == 1) {
                                    Bukkit.broadcastMessage(LobbySystem.getPrefix() + "§c§lDer Server wird in §6§l" + count + " §c§lSekunde Reloaded!");
                                    count--;
                                } else {
                                    Bukkit.broadcastMessage(LobbySystem.getPrefix() + "§c§lDer Server wird in §6§l" + count + " §c§lSekunden Reloaded!");
                                    count--;
                                }
                            }
                        }, 0, 10 * 2);
                    }
                }
            }

            //AdminItems: RestartServer!
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRestartServer")) {
                if (e.getItem() != null && e.getItem().getType() == Material.REDSTONE_BLOCK) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Player p = e.getPlayer();
                        p.getInventory().setItem(6, new ItemBuilder(Material.REDSTONE_BLOCK).setName("§c§lRestartServer").build());
                        taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.instance, new Runnable() {

                            int conutdown = 10;

                            @Override
                            public void run() {
                                if (conutdown < 1) {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        all.kickPlayer("§c§lDer Server wird Neu gestartet!");
                                    }
                                    Bukkit.spigot().restart();
                                    Bukkit.getScheduler().cancelTask(taskID2);
                                } else if (conutdown == 1) {
                                    Bukkit.broadcastMessage(LobbySystem.getPrefix() + "§c§lDer Server wird in §6§l" + conutdown + " §c§lSekunde Neu gestartet!");
                                    conutdown--;
                                } else {
                                    Bukkit.broadcastMessage(LobbySystem.getPrefix() + "§c§lDer Server wird in §6§l" + conutdown + " §c§lSekunden Neu gestartet!");
                                    conutdown--;
                                }
                            }
                        }, 0, 10 * 2);
                    }
                }
            }

        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (this.hide.contains(p) && p != e.getPlayer())
                p.hidePlayer(e.getPlayer());
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (this.hide.contains(e.getPlayer()))
            this.hide.remove(e.getPlayer());
    }
}
