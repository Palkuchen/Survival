package de.Palkuchen.survival.general;

import de.Palkuchen.survival.player.CustomPlayer;
import de.Palkuchen.survival.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        CustomPlayer customPlayer = PlayerHandler.getPlayer(player);
        event.setCancelled(true);
        String fullMessage = customPlayer.getGroup().getPrefix() + " §7- " + player.getName() + " » " + event.getMessage();

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(fullMessage);
        }
    }
}
