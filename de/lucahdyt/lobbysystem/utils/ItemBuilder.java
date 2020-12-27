package de.lucahdyt.lobbysystem.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private ItemStack itemStack;

    private ItemMeta meta;

    private SkullMeta skullMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
    }

    public ItemBuilder setMaterial(Material material) {
        this.itemStack.setType(material);
        return this;
    }

    public ItemBuilder setSubid(byte subid) {
        this.itemStack.getData().setData(subid);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        this.meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments) {
        enchantments.forEach((enchantment, level) -> this.itemStack.addEnchantment(enchantment, level.intValue()));
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, Integer level) {
        this.itemStack.addEnchantment(enchantment, level.intValue());
        return this;
    }

    public ItemBuilder clearEnchantments() {
        this.itemStack.getEnchantments().keySet().forEach(enchantment -> this.itemStack.removeEnchantment(enchantment));
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        this.itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.meta.setLore(lore);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        List<String> loreList = this.meta.getLore();
        loreList.add(lore);
        this.meta.setLore(loreList);
        return this;
    }

    public ItemBuilder clearLore() {
        this.meta.setLore(new ArrayList());
        return this;
    }

    public ItemBuilder removeLore(String lore) {
        this.meta.getLore().remove(lore);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        this.itemStack.setItemMeta(this.meta);
        this.skullMeta = (SkullMeta) this.itemStack.getItemMeta();
        this.skullMeta.setOwner(owner);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }

    public ItemStack buildSkull() {
        this.itemStack.setItemMeta((ItemMeta) this.skullMeta);
        this.itemStack.getData().setData((byte) 3);
        return this.itemStack;
    }
}
