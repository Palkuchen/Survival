package de.Palkuchen.survival.util;

import de.Palkuchen.survival.Survival;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.concurrent.Callable;

public class ItemBuilder {

    private final HashMap<ClickType, Runnable> invClickRunnable = new HashMap<>();

    private Material material;
    private String name;
    private int amount = 1;
    private long id = -1;
    private Color color;
    private String texture;
    private ItemStack stack;
    private Runnable interactRunnable;
    private Callable<String> desCall;
    private boolean withIdentification;

    private List<ItemFlag> itemFlags = new ArrayList<>();
    public HashMap<Enchantment, Integer> enchants = new HashMap<>();
    public static HashMap<Long, ItemBuilder> Items = new HashMap<>();

    public ItemBuilder(Material material, String name) {
        this.material = material;
        this.name = name;
        this.stack = create();
    }

    public ItemStack create() {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta im = item.getItemMeta();

        im.setDisplayName(name);
        try {
            if (desCall != null) {
                im.setLore(Arrays.stream(desCall.call().split("/n")).toList());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<Enchantment, Integer> i : enchants.entrySet()) {
            im.addEnchant(i.getKey(), i .getValue(), true);
        }

        if (color != null) {
            LeatherArmorMeta meta = (LeatherArmorMeta) im;
            meta.setColor(color);
        }

        if (withIdentification) addIdentificationID(im);
        for (ItemFlag itemFlag : itemFlags) {
            im.addItemFlags(itemFlag);
        }
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(im);
        return item;
    }

    public void addIdentificationID(ItemMeta meta) {
        NamespacedKey namespacedKey = new NamespacedKey(Survival.plugin, "id");
        Random rand = new Random();
        this.id = rand.nextLong();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.LONG, id);
        Items.put(id, this);
    }

    public static boolean hasIdentificationID(ItemStack stack) {
        NamespacedKey namespacedKey = new NamespacedKey(Survival.plugin, "id");
        PersistentDataContainer container = stack.getItemMeta().getPersistentDataContainer();
        return container.has(namespacedKey, PersistentDataType.LONG);
    }

    public boolean hasClickRunnable() {
        return invClickRunnable != null;
    }

    public boolean hasInteractRunnable() {
        return interactRunnable != null;
    }

    public static long getIdentificationID(ItemStack stack) {
        NamespacedKey namespacedKey = new NamespacedKey(Survival.plugin, "id");
        PersistentDataContainer container = stack.getItemMeta().getPersistentDataContainer();
        long ID = container.get(namespacedKey, PersistentDataType.LONG);
        return ID;
    }

    public ItemBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public ItemBuilder setHeadTexture(String texture) {
        this.texture = texture;
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        enchants.put(ench, level);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getItemName(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        return meta.getDisplayName();
    }

    public ItemBuilder addOnInvClick(ClickType type, Runnable runnable) {
        this.invClickRunnable.put(type, runnable);
        this.withIdentification = true;
        return this;
    }

    public ItemBuilder addInteract(Runnable runnable) {
        this.interactRunnable = runnable;
        this.withIdentification = true;
        return this;
    }

    public ItemBuilder setDesCall(Callable<String> desCall) {
        this.desCall = desCall;
        return this;
    }

    public Runnable getInteractRunnable() {
        return interactRunnable;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public HashMap<ClickType, Runnable> getInvClickRunnable() {
        return invClickRunnable;
    }

    public void addItemFlag(ItemFlag itemFlag) {
        itemFlags.add(itemFlag);
    }

    public long getId() {
        return id;
    }
}
