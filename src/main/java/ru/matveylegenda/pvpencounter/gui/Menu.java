package ru.matveylegenda.pvpencounter.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.matveylegenda.pvpencounter.PvPEncounter;
import ru.matveylegenda.pvpencounter.utils.configs.MenuConfig;

import static ru.matveylegenda.pvpencounter.utils.ColorParser.colorize;

public class Menu implements InventoryHolder {
    private final Inventory inv;

    private PvPEncounter plugin = PvPEncounter.getInstance();

    private MenuConfig menuConfig = PvPEncounter.getInstance().menuConfig;

    public Menu() {
        inv = Bukkit.getServer().createInventory(this, menuConfig.size, colorize(menuConfig.title));

        ItemStack joinItem = new ItemStack(Material.valueOf(menuConfig.items.join.material));

        ItemMeta joinMeta = joinItem.getItemMeta();
        joinMeta.setDisplayName(colorize(menuConfig.items.join.name));
        joinMeta.setLore(colorize(menuConfig.items.join.lore));
        joinMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        joinItem.setItemMeta(joinMeta);

        inv.setItem(menuConfig.items.join.slot, joinItem);


        ItemStack quitItem = new ItemStack(Material.valueOf(menuConfig.items.quit.material));

        ItemMeta quitMeta = quitItem.getItemMeta();
        quitMeta.setDisplayName(colorize(menuConfig.items.quit.name));
        quitMeta.setLore(colorize(menuConfig.items.quit.lore));
        quitMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        quitItem.setItemMeta(quitMeta);

        inv.setItem(menuConfig.items.quit.slot, quitItem);
    }

    @Override
    public Inventory getInventory() {
        return this.inv;
    }
}
