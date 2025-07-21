package de.Palkuchen.survival.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class Anchor implements CommandExecutor, TabExecutor {

    public static HashMap<String, Location> anchorMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player player) {
            String UUID = player.getUniqueId().toString();
            if (args.length < 1) {
                if (anchorMap.containsKey(UUID)) {
                    player.teleport(anchorMap.get(UUID));
                } else {
                    player.sendMessage("§7Du hast keinen Anchor");
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    anchorMap.put(UUID, player.getLocation());
                    player.sendMessage("§7Du hast dein Anchor gesetzt");
                } else {
                    player.sendMessage("§7Diese ausführung gibt es nicht");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) return List.of("set");
        return List.of();
    }
}
