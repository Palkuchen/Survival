package de.Palkuchen.survival.general;

import de.Palkuchen.survival.player.CustomPlayer;
import de.Palkuchen.survival.player.Group;
import de.Palkuchen.survival.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class TabManager extends BukkitRunnable {

    private final JavaPlugin plugin;

    public TabManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startUpdating() {
        runTaskTimer(plugin, 0L, 20L * 3);
    }

    @Override
    public void run() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        HashMap<Group, Team> teams = new HashMap<>();
        for (Group group : Group.values()) {
            Team teamTrusted = scoreboard.registerNewTeam(group.name());
            teamTrusted.setPrefix(group.getPrefix() + " §7- ");
            teamTrusted.setColor(ChatColor.GRAY);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            String header = "§7Unser §aSurvival §7Server\n";
            String footer = "\n§e§lAktuelle Spieler " + Bukkit.getOnlinePlayers().size();
            player.setPlayerListHeaderFooter(header, footer);

            CustomPlayer customPlayer = PlayerHandler.getPlayer(player);
            Group group = customPlayer.getGroup();
            teams.get(group).addEntry(player.getName());
            player.setScoreboard(scoreboard);
        }
    }
}
