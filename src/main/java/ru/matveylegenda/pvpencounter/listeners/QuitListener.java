package ru.matveylegenda.pvpencounter.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.matveylegenda.pvpencounter.utils.WaitingList;

public class QuitListener implements Listener {
    private WaitingList waitingList = new WaitingList();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (waitingList.contains(player)) {
            waitingList.remove(player);
        }
    }
}
