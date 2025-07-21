package de.Palkuchen.survival.enemys;

import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.EntityType;

public enum Enemys {
    Endermann(EntityType.ENDERMAN, World.Environment.NORMAL,5, 50, 55),
    STRONG_ENDERMANN(EntityType.ENDERMAN, World.Environment.THE_END,7, 60, 70);

    private World.Environment environment;
    private EntityType type;
    private double damage;
    private double health;
    private int level;

    Enemys(EntityType type, World.Environment environment, double damage, double health, int level) {
        this.environment = environment;
        this.type = type;
        this.damage = damage;
        this.health = health;
        this.level = level;
    }

    public EntityType getType() {
        return type;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public World.Environment getEnvironment() {
        return environment;
    }
}
