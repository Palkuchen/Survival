package de.Palkuchen.survival.util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

public class UtilityEvents implements Listener {

    @EventHandler
    public void onCustomItemClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getInventory().getType() != InventoryType.CHEST) return;
        if (!ItemBuilder.hasIdentificationID(e.getCurrentItem())) return;
        long ID = ItemBuilder.getIdentificationID(e.getCurrentItem());

        if (ItemBuilder.Items.containsKey(ID)) {
            ItemBuilder builder = ItemBuilder.Items.get(ID);
            if (builder.hasClickRunnable()) {
                e.setCancelled(true);
                if (builder.getInvClickRunnable().containsKey(e.getClick()))
                    builder.getInvClickRunnable().get(e.getClick()).run();
            }
        }
    }

    @EventHandler
    public void onCustomItemClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (e.getItem() == null) return;
        if (e.getItem().getItemMeta() == null) return;
        if (e.getItem().getItemMeta().getDisplayName().length() < 3) return;

        if (!ItemBuilder.hasIdentificationID(e.getItem())) return;
        long ID = ItemBuilder.getIdentificationID(e.getItem());

        if (ItemBuilder.Items.containsKey(ID)) {
            ItemBuilder builder = ItemBuilder.Items.get(ID);
            if (!builder.hasInteractRunnable()) return;
            e.setCancelled(true);
            builder.getInteractRunnable().run();
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        // InventoryBuilder.removeInventory(event.getInventory());
    }
}
