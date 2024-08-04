package ru.matveylegenda.pvpencounter.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.matveylegenda.pvpencounter.PvPEncounter;
import ru.matveylegenda.pvpencounter.utils.configs.MainConfig;
import ru.matveylegenda.pvpencounter.utils.configs.MenuConfig;

import java.util.List;

import static ru.matveylegenda.pvpencounter.utils.ColorParser.colorize;

public class Menu implements InventoryHolder {
    private final Inventory inv;

    private PvPEncounter plugin = PvPEncounter.getInstance();

    private MenuConfig menuConfig = plugin.menuConfig;
    private MainConfig mainConfig = plugin.mainConfig;

    public Menu() {
        inv = Bukkit.getServer().createInventory(this, menuConfig.size, colorize(menuConfig.title, mainConfig.serializer));

        Material joinMaterial = Material.valueOf(menuConfig.items.join.material);
        String joinName = menuConfig.items.join.name;
        List<String> joinLore = menuConfig.items.join.lore;

        inv.setItem(menuConfig.items.join.slot, addItem(joinMaterial, joinName, joinLore));

        Material quitMaterial = Material.valueOf(menuConfig.items.quit.material);
        String quitName = menuConfig.items.quit.name;
        List<String> quitLore = menuConfig.items.quit.lore;

        inv.setItem(menuConfig.items.quit.slot, addItem(quitMaterial, quitName, quitLore));
    }

    private ItemStack addItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (name != null) {
            meta.setDisplayName(colorize(name, mainConfig.serializer));
        }

        if (lore != null) {
            meta.setLore(colorize(lore, mainConfig.serializer));
        }

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public Inventory getInventory() {
        return this.inv;
    }
}
