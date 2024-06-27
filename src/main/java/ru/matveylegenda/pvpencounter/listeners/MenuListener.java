package ru.matveylegenda.pvpencounter.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ru.matveylegenda.pvpencounter.PvPEncounter;
import ru.matveylegenda.pvpencounter.gui.Menu;
import ru.matveylegenda.pvpencounter.utils.TeleportUtil;
import ru.matveylegenda.pvpencounter.utils.WaitingList;
import ru.matveylegenda.pvpencounter.utils.configs.MainConfig;
import ru.matveylegenda.pvpencounter.utils.configs.MenuConfig;
import ru.matveylegenda.pvpencounter.utils.configs.MessagesConfig;

import static ru.matveylegenda.pvpencounter.utils.ColorParser.colorize;

public class MenuListener implements Listener {
    private MainConfig mainConfig = PvPEncounter.getInstance().mainConfig;
    private MenuConfig menuConfig = PvPEncounter.getInstance().menuConfig;
    private MessagesConfig messagesConfig = PvPEncounter.getInstance().messagesConfig;

    private WaitingList waitingList = new WaitingList();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory.getHolder() instanceof Menu) {
            Player player = (Player) event.getWhoClicked();

            event.setCancelled(true);

            if (event.getSlot() == menuConfig.items.join.slot) {
                if (waitingList.contains(player)) {
                    player.sendMessage(colorize(messagesConfig.alreadyQueue));
                    player.closeInventory();

                    return;
                }

                waitingList.add(player);

                player.sendMessage(colorize(messagesConfig.joinQueue));
                player.closeInventory();

                if (mainConfig.joinBroadcastMessageEnabled) {
                    if (waitingList.getSize() == 1) {
                        String broadcastMessage = messagesConfig.joinBroadcastMessage
                                .replace("{player}", player.getName());
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(colorize(broadcastMessage));
                        }
                    }
                }

                if (waitingList.getSize() >= 2) {
                    waitingList.shuffle();

                    Player player1 = waitingList.get(0);
                    Player player2 = waitingList.get(1);

                    waitingList.remove(player1);
                    waitingList.remove(player2);

                    // Логика телепорта, позже короче

                    player1.sendMessage(colorize(
                            messagesConfig.teleportPlayer
                                    .replace("{player}", player2.getName())
                    ));

                    player2.sendMessage(colorize(
                            messagesConfig.teleportPlayer
                                    .replace("{player}", player1.getName())
                    ));

                    World world = Bukkit.getWorld(mainConfig.randomLocation.world);

                    int minX = mainConfig.randomLocation.minX;
                    int maxX = mainConfig.randomLocation.maxX;

                    int minZ = mainConfig.randomLocation.minZ;
                    int maxZ = mainConfig.randomLocation.maxZ;

                    Location location = new TeleportUtil().getRandomLocation(world, minX, maxX, minZ, maxZ);

                    if (location == null) {
                        player1.sendMessage(colorize(messagesConfig.locationNotFound));
                        player2.sendMessage(colorize(messagesConfig.locationNotFound));

                        return;
                    }

                    player1.teleport(location);
                    player2.teleport(location);
                }
            }

            if (event.getSlot() == menuConfig.items.quit.slot) {
                if (!waitingList.contains(player)) {
                    player.sendMessage(colorize(messagesConfig.notQueue));
                    player.closeInventory();

                    return;
                }

                waitingList.remove(player);

                player.sendMessage(colorize(messagesConfig.quitQueue));
                player.closeInventory();
            }
        }
    }
}
