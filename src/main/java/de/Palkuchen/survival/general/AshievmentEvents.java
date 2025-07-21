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
        String key = advancement.getKey().getKey();

        if (key.contains("obtain")) return;
        if (key.contains("recipes")) return;
        player.sendMessage("§7Du hast das Advancement §e" + advancement.getDisplay().getTitle() + "§7 abgeschlossen!");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8f, 1.0f);
    }
}
