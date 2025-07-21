package de.Palkuchen.survival.enemys;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnEvents implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Monster monster) {
            morpf(monster);
        } else if (entity instanceof Animals animal) {
            morpf(animal);
        }
    }

    public void morpf(LivingEntity livingEntity) {
        for (Enemys enemys : Enemys.values()) {
            if (enemys.getType() == livingEntity.getType()) {
                if (livingEntity.getWorld().getEnvironment() == enemys.getEnvironment()) {
                    int level = enemys.getLevel();
                    livingEntity.setCustomName("§8[§a" + level + "§8] §7" + livingEntity.getType().getName());
                    livingEntity.setCustomNameVisible(true);
                    livingEntity.setMaxHealth(enemys.getHealth());
                    livingEntity.setHealth(enemys.getHealth());
                    livingEntity.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(enemys.getDamage());
                    return;
                }
            }
        }
        int level = (int) livingEntity.getMaxHealth();
        livingEntity.setCustomName("§8[§a" + level + "§8] §7" + livingEntity.getType().getName());
        livingEntity.setCustomNameVisible(true);
    }
}
