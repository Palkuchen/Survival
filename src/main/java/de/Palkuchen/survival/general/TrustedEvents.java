package de.Palkuchen.survival.general;

import de.Palkuchen.survival.player.CustomPlayer;
import de.Palkuchen.survival.player.Group;
import de.Palkuchen.survival.player.PlayerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TrustedEvents implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        CustomPlayer customPlayer = PlayerHandler.getPlayer(event.getPlayer());
        if (customPlayer.getGroup() == Group.VISITOR) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§7Als Besucher darfst du nichts abbauen");
        }
    }

    @EventHandler
    public void onBreak(BlockPlaceEvent event) {
        CustomPlayer customPlayer = PlayerHandler.getPlayer(event.getPlayer());
        if (customPlayer.getGroup() == Group.VISITOR) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§7Als Besucher darfst du nichts platzieren");
        }
    }

    @EventHandler
    public void onBreak(PlayerInteractEvent event) {
        CustomPlayer customPlayer = PlayerHandler.getPlayer(event.getPlayer());
        if (customPlayer.getGroup() == Group.VISITOR) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§7Als Besucher darfst als Besucher mit nix interagieren");
        }
    }
}
