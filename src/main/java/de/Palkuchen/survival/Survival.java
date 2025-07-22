package de.Palkuchen.survival;


import de.Palkuchen.survival.commands.Anchor;
import de.Palkuchen.survival.commands.GmCommand;
import de.Palkuchen.survival.commands.MsgCommand;
import de.Palkuchen.survival.commands.Permission;
import de.Palkuchen.survival.enemys.FightEvents;
import de.Palkuchen.survival.general.AshievmentEvents;
import de.Palkuchen.survival.general.ChatEvents;
import de.Palkuchen.survival.general.ConnectionEvents;
import de.Palkuchen.survival.general.TabManager;
import de.Palkuchen.survival.player.PlayerDamageEvents;
import de.Palkuchen.survival.player.PlayerDeathEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Survival extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("besteatigung").setExecutor(new MsgCommand());
        getCommand("do").setExecutor(new GmCommand());
        // Plugin startup logic
        getCommand("anchor").setExecutor(new Anchor());
        getCommand("permission").setExecutor(new Permission());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AshievmentEvents(), this);
        pm.registerEvents(new ChatEvents(), this);
        pm.registerEvents(new PlayerDamageEvents(), this);
        pm.registerEvents(new PlayerDeathEvents(), this);
        pm.registerEvents(new FightEvents(), this);
        pm.registerEvents(new ConnectionEvents(), this);
        new TabManager(this).startUpdating();
    }

    @Override
    public void onDisable() {

    }
}
