package de.Palkuchen.survival.enemys;

import de.Palkuchen.survival.Survival;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Comparator;

public class FightEvents implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player damaged) {
            if (event.getDamager() instanceof  Player attacker) {
                event.setDamage(event.getDamage()*0.5);
                return;
            }
            event.setDamage(event.getDamage()*1.5);
        }
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(event.getProjectile() instanceof Arrow arrow)) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead() || arrow.isOnGround()) {
                    this.cancel();
                    return;
                }

                LivingEntity target = getNearestTarget(player, arrow.getLocation(), 4);

                if (target != null) {
                    Vector currentVelocity = arrow.getVelocity();
                    Vector toTarget = target.getLocation().add(0,0.4,0).toVector().
                            subtract(arrow.getLocation().toVector()).normalize();

                    double blendFactor = 0.25;
                    Vector newVelocity = currentVelocity.multiply(1 - blendFactor).add(toTarget.multiply(currentVelocity.length() * blendFactor));
                    arrow.setVelocity(newVelocity);
                }
            }
        }.runTaskTimer(Survival.plugin, 1L, 1L);
    }


    private LivingEntity getNearestTarget(Player shooter, Location from, double radius) {
        return from.getWorld().getNearbyEntities(from, radius, radius, radius).stream()
                .filter(e -> e instanceof LivingEntity)
                .filter(e -> !(e instanceof Player))
                .map(e -> (LivingEntity) e)
                .min(Comparator.comparingDouble(e -> e.getLocation().distanceSquared(from)))
                .orElse(null);
    }

}
