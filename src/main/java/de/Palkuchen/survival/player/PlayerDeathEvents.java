package de.Palkuchen.survival.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathEvents implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.setGameMode(GameMode.SPECTATOR);
        player.sendTitle("§4Death", "§7Damit bist du ausgeschieden");
        event.setDeathMessage(null);
        Bukkit.broadcastMessage("§8[§c?§8] §7" + player.getName() + " §7ist ausgeschieden");
    }
}
