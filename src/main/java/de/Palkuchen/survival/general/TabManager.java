package de.Palkuchen.survival.general;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

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

        Team teamMod = scoreboard.registerNewTeam("MOD");
        teamMod.setPrefix(ChatColor.AQUA + "[Trusted] ");
        teamMod.setColor(ChatColor.GRAY);

        Team teamDefault = scoreboard.registerNewTeam("Gast");
        teamDefault.setPrefix(ChatColor.GRAY + "[Gast] ");
        teamDefault.setColor(ChatColor.GRAY);

        for (Player player : Bukkit.getOnlinePlayers()) {
            // Tablist-Header/Footer setzen
            String header = "§7Unser §aSurvival §7Server/n";
            String footer = "/n§e§lAktuelle Spieler " + Bukkit.getOnlinePlayers().size();
            player.setPlayerListHeaderFooter(header, footer);

            // Rechte prüfen und Spieler zu Teams zuweisen
            if (player.hasPermission("admin")) {
                teamMod.addEntry(player.getName());
            } else {
                teamDefault.addEntry(player.getName());
            }

            player.setScoreboard(scoreboard);
        }
    }
}
