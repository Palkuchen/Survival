package de.Palkuchen.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        String message = "Ich habe noch stärkere schmerzen!";

        sender.sendMessage(message);

        return false;
    }
}
