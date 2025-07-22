package de.Palkuchen.survival;

import de.Palkuchen.survival.commands.DoCommand;
import de.Palkuchen.survival.commands.Anchor;
import de.Palkuchen.survival.commands.MsgCommand;
import de.Palkuchen.survival.commands.Permission;
import de.Palkuchen.survival.commands.SkillCommand;
import de.Palkuchen.survival.enemys.FightEvents;
import de.Palkuchen.survival.enemys.SpawnEvents;
import de.Palkuchen.survival.general.*;
import de.Palkuchen.survival.player.PlayerDamageEvents;
import de.Palkuchen.survival.player.PlayerDeathEvents;
import de.Palkuchen.survival.util.UtilityEvents;
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
        getCommand("do").setExecutor(new DoCommand());
        getCommand("anchor").setExecutor(new Anchor());
        getCommand("skill").setExecutor(new SkillCommand());
        getCommand("permission").setExecutor(new Permission());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AshievmentEvents(), this);
        pm.registerEvents(new ChatEvents(), this);
        pm.registerEvents(new PlayerDamageEvents(), this);
        pm.registerEvents(new PlayerDeathEvents(), this);
        pm.registerEvents(new FightEvents(), this);
        pm.registerEvents(new ConnectionEvents(), this);
        pm.registerEvents(new SpawnEvents(), this);
        pm.registerEvents(new UtilityEvents(), this);
        pm.registerEvents(new TrustedEvents(), this);

        new TabManager(this).startUpdating();
    }

    @Override
    public void onDisable() {

    }
}
