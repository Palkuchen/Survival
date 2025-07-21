package de.Palkuchen.survival.general;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Bukkit.broadcastMessage("§8[§a+§8] §3" + event.getPlayer() + " §7ist beigetreten");
    }
}
