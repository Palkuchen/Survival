package de.Palkuchen.survival.general;

import org.bukkit.Sound;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AshievmentEvents implements Listener {

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        Advancement advancement = event.getAdvancement();
        String key = advancement.getKey().getKey(); // z.B. "story/mine_diamond"

        // Eigene Nachricht senden
        player.sendMessage("§7Du hast das Advancement §e" + key + "§7 abgeschlossen!");

        // Optional: Sound oder Titel
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        player.sendTitle("Erfolg!", "Du bist ein Champion!", 10, 70, 20);
    }
}
