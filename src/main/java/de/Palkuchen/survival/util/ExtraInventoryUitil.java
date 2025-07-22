package de.Palkuchen.survival.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExtraInventoryUitil {
    public static int getAmountItems(Inventory inv, Material m) {
        int total = 0;
        for (ItemStack item : inv.getContents()) {
            if (item != null) {
                if (item.getType() == m) {
                    total += item.getAmount();
                }
            }
        }
        return total;
    }

    public static void addItemSavely(Player p, ItemStack toAdd) {
        Inventory inv = p.getInventory();
        Location location = p.getLocation();
        if (hasAvailableSlot(p, toAdd)) {
            inv.addItem(toAdd);
        } else {
            location.getWorld().dropItemNaturally(location, toAdd);
        }
    }

    public static boolean hasAvailableSlot(Player player, ItemStack toAdd) {
        Inventory inv = player.getInventory();
        for (int i = 0; i < 36; i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) {
                return true;
            }
            if (item.isSimilar(toAdd)) {
                if (item.getAmount() + toAdd.getAmount() <= 64) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int removeItems(Player p, Material mat) {
        int amount = 0;
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            if (p.getInventory().getItem(i) != null) {
                if (p.getInventory().getItem(i).getType().equals(mat)) {
                    amount += p.getInventory().getItem(i).getAmount();
                    p.getInventory().setItem(i, null);
                }
            }
        }
        return amount;
    }
}
