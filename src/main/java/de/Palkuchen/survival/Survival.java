package de.Palkuchen.survival;

import de.Palkuchen.survival.enemys.FightEvents;
import de.Palkuchen.survival.general.ConnectionEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Survival extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new FightEvents(), this);
        pm.registerEvents(new ConnectionEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
