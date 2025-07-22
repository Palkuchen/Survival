package de.Palkuchen.survival.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class InventoryBuilder {

    public static final HashMap<Inventory, InventoryBuilder> inventoryBuilder = new HashMap<>();

    private int size;
    private String title;
    private InventoryBuilder lastInv;
    private Runnable closeRunnable;
    private Runnable updateRunnable;

    private HashMap<Integer, ItemBuilder> itemBuilders = new HashMap<>();

    public InventoryBuilder(int size, String title) {
        this.size = size;
        this.title = title;
    }

    public Inventory getInventory(Player player) {
        Inventory result = Bukkit.createInventory(null, size, title);
        update(result);
        if (lastInv != null) {
            ItemBuilder builder = new ItemBuilder(Material.ARROW, "§8Back");
            builder.addOnInvClick(ClickType.LEFT, () -> {
                player.closeInventory();
                try {
                    player.openInventory(lastInv.getInventory(player));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            result.setItem(size - 1, builder.create());
        }

        inventoryBuilder.put(result, this);
        return result;
    }

    public static InventoryBuilder getInvBuilder(Inventory inv) {
        if (!inventoryBuilder.containsKey(inv)) return null;
        return inventoryBuilder.get(inv);
    }

    public void update(Inventory result) {
        itemBuilders.forEach((index, itemBuilder) -> result.setItem(index, itemBuilder.create()));
    }

    public void setGlass(GlassType type) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (itemBuilders.get(i) == null) {
                ItemBuilder builder = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE, "§9");
                builder.addOnInvClick(ClickType.SHIFT_LEFT, ()->{});
                if (type == GlassType.Round) {
                    if (i < 9 || i > size - 9 || i % 9 == 0 || i % 9 == 8) {
                        setItem(index, builder);
                    }
                } else if (type == GlassType.ItemRefined) {
                    if (i < 9 || i > size - 9 || i % 9 == 0 || i % 9 == 1 || i % 9 == 2 || i % 9 == 8) {
                        if (i != 19) setItem(index, builder);
                    }
                } else {
                    setItem(index, builder);
                }
            }
            index++;
        }
    }

    public void setItem(int index , ItemBuilder builder) {
        itemBuilders.put(index, builder);
    }

    public void removeItem(int index) {
        if (itemBuilders.containsKey(index)) itemBuilders.remove(index);
    }

    public boolean hasItem(int index) {
        return itemBuilders.containsKey(index);
    }

    public int getSize() {
        return size;
    }

    public void setLastInv(InventoryBuilder lastInv) {
        this.lastInv = lastInv;
    }

    public HashMap<Integer, ItemBuilder> getItemBuilders() {
        return itemBuilders;
    }
}

