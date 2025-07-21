package de.Palkuchen.survival.player;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerHandler {

    public static HashMap<String, CustomPlayer> customPlayerHashMap = new HashMap<>();

    public static CustomPlayer getPlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        if (customPlayerHashMap.containsKey(uuid)) return customPlayerHashMap.get(uuid);
        else {
            CustomPlayer customPlayer = new CustomPlayer(uuid);
            customPlayerHashMap.put(uuid, customPlayer);
            return customPlayer;
        }
    }
}
