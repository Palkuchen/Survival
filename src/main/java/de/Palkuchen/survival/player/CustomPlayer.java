package de.Palkuchen.survival.player;

import de.Palkuchen.survival.Survival;
import de.Palkuchen.survival.util.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CustomPlayer {

    private List<String> tpaRequests = new ArrayList<>();

    private Group group = Group.VISITOR;
    private String uuid;

    public CustomPlayer(String uuid) {
        this.uuid = uuid;
    }

    public void load() {
        YamlConfiguration config = Configuration.getConfiguration().getYamlConfiguration();
        if (!config.contains("players." + uuid)) save();
        group = Group.valueOf(config.getString("players." + uuid + ".group"));
    }

    public void save() {
        YamlConfiguration config = Configuration.getConfiguration().getYamlConfiguration();
        config.set("players." + uuid + ".group", group.name());
        Survival.plugin.saveConfig();
    }

    public void addTpa(String uuid) {
        tpaRequests.add(uuid);
    }

    public boolean hasTpa(String uuid) {
        return tpaRequests.contains(uuid);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        save();
    }
}
