package de.Palkuchen.survival.general;

import de.Palkuchen.survival.player.CustomPlayer;
import de.Palkuchen.survival.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConnectionEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2000000, 1, true));
        event.setJoinMessage(null);
        Bukkit.broadcastMessage("§8[§a+§8] §3" + event.getPlayer().getName() + " §7ist beigetreten");
        CustomPlayer customPlayer = PlayerHandler.getPlayer(player);
        customPlayer.load();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Bukkit.broadcastMessage("§8[§c-§8] §3" + event.getPlayer().getName() + " §7hat den Server verlassen");
    }
}
