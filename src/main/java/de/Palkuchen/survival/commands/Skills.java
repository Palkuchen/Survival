package de.Palkuchen.survival.commands;

import org.bukkit.Material;

public enum Skills {
    COMBAT("Angriff", "§7Dieser Skill gibt dir +1% \nSchaden pro Level", Material.IRON_SWORD),
    MINING("Bergbau", "§7Du hast die chance auf double drops", Material.IRON_PICKAXE),
    FORAGING("Holzfäller", "§7Lumberjack, öhhm und mehr",Material.IRON_AXE),
    ARCHERY("Scharfschütze", "§7Obvius", Material.BOW),
    MERCHANT("Handwerker", "Sehen wa was wird", Material.CRAFTING_TABLE),
    FARMING("Farmer", "§7Sum SUm", Material.IRON_HOE);

    private String name;
    private String description;
    private Material display;

    Skills(String name, String description, Material display) {
        this.name = name;
        this.display = display;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Material getDisplay() {
        return display;
    }
}
