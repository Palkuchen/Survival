package de.Palkuchen.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GRAY + "Du kein Sterblicher!");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("*")){
            sender.sendMessage(ChatColor.GRAY + "Du hast nicht die rechte");
            return false;

        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("1")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(ChatColor.GRAY + "Du bist nun eine höhere existenz");
            } else if (args[0].equalsIgnoreCase("0")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.GRAY + "Du bist nun eine minderwertige existenz");
            }else if (args[0].equalsIgnoreCase("3")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(ChatColor.BLACK + "Du bist nun ein geist");
            }else if (args[0].equalsIgnoreCase("2")) {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(ChatColor.BLACK + "Du bist nun nutzlos");
            } else {
                sender.sendMessage(ChatColor.GRAY + "Du bist inkompetent!");
            }

        } else {
            sender.sendMessage(ChatColor.GRAY + "Du bist inkompetent!");
        }
        return false;
    }

}
