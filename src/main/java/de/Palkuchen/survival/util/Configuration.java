package de.Palkuchen.survival.util;

import de.Palkuchen.survival.Survival;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration {

    private static Configuration configuration;
    private YamlConfiguration yamlConfiguration;

    public Configuration() {
        yamlConfiguration = (YamlConfiguration) Survival.plugin.getConfig();
    }

    public static Configuration getConfiguration() {
        if (configuration != null) return  configuration;
        configuration = new Configuration();
        return configuration;
    }

    public YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }
}
