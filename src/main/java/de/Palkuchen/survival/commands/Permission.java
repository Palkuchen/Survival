package de.Palkuchen.survival.commands;

import de.Palkuchen.survival.player.CustomPlayer;
import de.Palkuchen.survival.player.Group;
import de.Palkuchen.survival.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permission implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("*")) {
                player.sendMessage("§7Dazu hast du keine Rechte");
                return false;
            }
            if (args.length != 4) {
                player.sendMessage("§7Du hast den Command falsch eingegeben");
            }
            if (args[0].equalsIgnoreCase("set")) {
                String name = args[1];
                Player target = Bukkit.getPlayer(name);
                if (target == null) {
                    player.sendMessage("§7Dieser Spieler ist nicht Online");
                    return false;
                }
                if (args[2].equalsIgnoreCase("group")) {
                    Group group = Group.valueOf(args[3].toUpperCase());
                    if (group == null) {
                        player.sendMessage("§7Diese Gruppe gibt es nicht");
                        return false;
                    }

                    CustomPlayer customPlayer = PlayerHandler.getPlayer(target);
                    customPlayer.setGroup(group);

                    player.sendMessage("§7Du hast '§6" + target.getName() + "§7' die Gruppe " + args[3] + " gegeben");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) return List.of("set");
        if (args.length == 3) return List.of("group");
        if (args.length == 4) {
            List<String> groups = new ArrayList<>();
            Arrays.stream(Group.values()).toList().forEach(each -> groups.add(each.name().toLowerCase()));
            return groups;
        }
        return List.of();
    }
}
