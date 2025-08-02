package de.Palkuchen.survival.commands;

import de.Palkuchen.survival.player.CustomPlayer;
import de.Palkuchen.survival.util.GlassType;
import de.Palkuchen.survival.util.InventoryBuilder;
import de.Palkuchen.survival.util.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class SkillManager {

    public void openSkills(Player player) {
        InventoryBuilder inventoryBuilder = new InventoryBuilder(9*5, "§8Skills") ;
        inventoryBuilder.setGlass(GlassType.Full);

        List<Integer> positions = List.of(12, 14, 20, 24, 30, 32);
        Skills[] skills = Skills.values();
        int counter = 0;
        for (int pos : positions) {
            Skills skill = skills[counter];
            ItemBuilder skillBuilder = new ItemBuilder(skill.getDisplay(), skill.getName());
            skillBuilder.setDesCall(() -> skill.getDescription());
            skillBuilder.addOnInvClick(ClickType.LEFT, () -> {});
            inventoryBuilder.setItem(pos, skillBuilder);
            counter++;
        }

        player.openInventory(inventoryBuilder.getInventory(player));
    }
}
