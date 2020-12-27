package de.lucahdyt.lobbysystem.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyInventory {
    Player p;

    public LobbyInventory(Player p) {
        this.p = p;
    }

    public void setLobbyInventory() {

        //OP Spieler!

        if (p.isOp()) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§6§lTeleporter").build());
            p.getInventory().setItem(2, new ItemBuilder(Material.STICK).setName("§7§lSpieler verstecken").build());
            p.getInventory().setItem(4, new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§c§lAdminItems").build());
            p.updateInventory();
        } else {

            //Normale Spieler!

            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§6§lTeleporter").build());
            p.getInventory().setItem(2, new ItemBuilder(Material.STICK).setName("§7§lSpieler verstecken").build());
            p.getInventory().setItem(4, new ItemBuilder(Material.BARRIER).setName("§c§lKein Gadget").build());
            p.getInventory().setItem(6, new ItemBuilder(Material.CHEST).setName("§6§lGadgets").build());
            p.getInventory().setItem(8, new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (short) 3)).setName("§6§lDein Profil").setSkullOwner(p.getName()).buildSkull());
            p.updateInventory();
        }
    }
}
