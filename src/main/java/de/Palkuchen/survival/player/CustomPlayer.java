package de.Palkuchen.survival.player;

import de.Palkuchen.survival.Survival;
import de.Palkuchen.survival.commands.Skills;
import de.Palkuchen.survival.util.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomPlayer {

    private List<String> tpaRequests = new ArrayList<>();

    private HashMap<Skills, Double> skillExperince = new HashMap<>();
    private Group group = Group.VISITOR;
    private String uuid;

    public CustomPlayer(String uuid) {
        this.uuid = uuid;
    }

    public void load() {
        YamlConfiguration config = Configuration.getConfiguration().getYamlConfiguration();
        if (!config.contains("players." + uuid)) save();
        skillExperince.keySet().forEach(each -> {
            if (config.contains("players."+uuid+ ".skills."+each)) save();
        });
        skillExperince.keySet().forEach(each -> skillExperince.put(each, (Double)
                config.get("players."+uuid+ ".skills."+each)));
        group = Group.valueOf(config.getString("players." + uuid + ".group"));
    }

    public void save() {
        new BukkitRunnable() {
            @Override
            public void run() {
                YamlConfiguration config = Configuration.getConfiguration().getYamlConfiguration();
                config.set("players." + uuid + ".group", group.name());
                if (skillExperince.isEmpty()) {
                    skillExperince.keySet().forEach(each -> skillExperince.put(each, 0.0));
                }
                skillExperince.keySet().forEach(each -> config.set("players." + uuid + ".skills."+each,
                        skillExperince.get(each)));
                Survival.plugin.saveConfig();
            }
        }.runTaskAsynchronously(Survival.plugin);
    }

    public void addSkillExperince(Skills skills, double xp) {
        skillExperince.put(skills, xp);
        save();
    }

    public double getSkillExperince(Skills skill) {
        return skillExperince.get(skill);
    }

    public int getLevel(Skills skill) {
        double xp = skillExperince.get(skill);
        return (int) Math.pow(xp/100, 0.7);
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

    public HashMap<Skills, Double> getSkillExperince() {
        return skillExperince;
    }

    public void setSkillExperince(HashMap<Skills, Double> skillExperince) {
        this.skillExperince = skillExperince;
    }
}
