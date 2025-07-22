package de.Palkuchen.survival.commands;

import org.bukkit.Material;

public enum Skills {
    COMBAT("Angriff", Material.IRON_SWORD), MINING("Bergbau", Material.IRON_PICKAXE),
    FORAGING("Holzfäller", Material.IRON_AXE), ARCHERY("Scharfschütze", Material.BOW),
    MERCHANT("Handwerker", Material.CRAFTING_TABLE), FARMING("Farmer", Material.IRON_HOE);

    private String name;
    private Material display;

    Skills(String name, Material display) {
        this.name = name;
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public Material getDisplay() {
        return display;
    }
}
