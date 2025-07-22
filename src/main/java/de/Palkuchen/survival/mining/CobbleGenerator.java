package de.Palkuchen.survival.mining;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import static de.Palkuchen.survival.Survival.plugin;

public class CobbleGenerator implements Listener {

    @EventHandler
    public void onBlockForm(BlockFromToEvent event) {
        if (event.getBlock().getType() == Material.LAVA &&
                event.getToBlock().getType() == Material.WATER) {
            if (Math.random() < 0.01) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    event.getToBlock().setType(Material.GOLD_ORE);
                }, 1L);
            }
        }
    }
}
