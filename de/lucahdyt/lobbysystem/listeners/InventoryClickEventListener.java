package de.lucahdyt.lobbysystem.listeners;

import de.lucahdyt.lobbysystem.LobbySystem;
import de.lucahdyt.lobbysystem.utils.ItemBuilder;
import de.lucahdyt.lobbysystem.utils.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickEventListener implements Listener {

    private LobbySystem lobbySystem;

    public Inventory hatsInventory;

    public InventoryClickEventListener(LobbySystem lobbySystem) {
        this.lobbySystem = lobbySystem;
        Bukkit.getPluginManager().registerEvents(this, lobbySystem);

        hatsInventory = Bukkit.createInventory(null, 9, "§6§lHüte");
        hatsInventory.addItem(new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§6§lKetten Helm").build());
        hatsInventory.addItem(new ItemBuilder(Material.DIAMOND_HELMET).setName("§6§lDiamant Helm").build());
        hatsInventory.addItem(new ItemBuilder(Material.GLASS).setName("§6§lGlass").build());
        hatsInventory.setItem(8, new ItemBuilder(Material.BARRIER).setName("§c§lKeinen Hut").build());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {

            //Teleporter!
            if (e.getClickedInventory().getName().equalsIgnoreCase("§6§lTeleporter")) {
                if (e.getCurrentItem() != null) {
                    if (!e.getCurrentItem().hasItemMeta()) {
                        e.getWhoClicked().closeInventory();
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String displayname = e.getCurrentItem().getItemMeta().getDisplayName();
                        if (TeleportUtils.locationHashMap.containsKey(displayname)) {
                            e.getWhoClicked().closeInventory();
                            e.getWhoClicked().sendMessage(LobbySystem.getPrefix() + "§aDu wurdest zum Warp §6" + displayname + "§a teleportiert!");
                            e.getWhoClicked().teleport((Location) TeleportUtils.locationHashMap.get(displayname));
                        }
                    }
                }
                e.setCancelled(true);
            }

            //Gadgets!
            if (e.getClickedInventory().getName().equalsIgnoreCase("§6§lGadgets")) {
                Player p = (Player) e.getWhoClicked();
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                    Material material = e.getCurrentItem().getType();
                    if (material == Material.ENDER_PEARL) {
                        p.getInventory().setItem(4, new ItemBuilder(Material.ENDER_PEARL).setName("§b§lEnderperle").build());
                    } else if (material == Material.FIREWORK) {
                        p.getInventory().setItem(4, new ItemBuilder(Material.FIREWORK).setName("§b§lFeuerwerk").build());
                    } else if (material == Material.BARRIER) {
                        p.getInventory().setItem(4, new ItemBuilder(Material.BARRIER).setName("§c§lKein Gadget ausgewählt").build());
                    } else {
                        p.sendMessage(LobbySystem.getPrefix() + "§cDieses Gadget funktioniert noch nicht.");
                    }
                    p.closeInventory();
                    p.updateInventory();
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 3.0F, 1.0F);
                }
            }

            //Dein Profil!
            if (e.getClickedInventory().getName().equalsIgnoreCase("§6§lDein Profil")) {
                Player p = (Player) e.getWhoClicked();
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                    Material material = e.getCurrentItem().getType();
                    if (material == Material.CHAINMAIL_HELMET) {
                        p.openInventory(this.hatsInventory);
                        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 3.0F, 1.0F);
                        e.setCancelled(true);
                    }
                }
            }

            //Hüte!
            if (e.getClickedInventory().getName().equalsIgnoreCase("§6§lHüte")) {
                Player p = (Player) e.getWhoClicked();
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                    Material material = e.getCurrentItem().getType();
                    if (material != Material.BARRIER) {
                        p.getInventory().setHelmet(e.getCurrentItem());
                    } else {
                        p.getInventory().setHelmet(null);
                    }
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 3.0F, 1.0F);
                    p.closeInventory();
                    p.updateInventory();
                    e.setCancelled(true);
                }
            }

            //AdminItems!
            if (e.getClickedInventory().getName().equalsIgnoreCase("§c§lAdminItems")) {
                Player p = (Player) e.getWhoClicked();
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                    Material material = e.getCurrentItem().getType();
                    if (material == Material.BARRIER) {
                        p.getInventory().setItem(6, null);
                        p.closeInventory();
                        p.updateInventory();
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lFly")) {
                        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.FEATHER) {
                            e.setCancelled(true);
                            p.getInventory().setItem(6, new ItemBuilder(Material.FEATHER).setName("§c§lFly").build());
                            p.closeInventory();
                            p.updateInventory();
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lReloadServer")) {
                        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.BEACON) {
                            p.getInventory().setItem(6, new ItemBuilder(Material.BEACON).setName("§c§lReloadServer").build());
                            p.closeInventory();
                            p.updateInventory();
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRestartServer")) {
                        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                            p.getInventory().setItem(6, new ItemBuilder(Material.REDSTONE_BLOCK).setName("§c§lRestartServer").build());
                            p.closeInventory();
                            p.updateInventory();
                        }
                    }
                }
            }
        }
    }
}
