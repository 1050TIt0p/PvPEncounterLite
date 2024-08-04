package ru.matveylegenda.pvpencounter.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class WaitingList {
    private static ArrayList<UUID> waitingPlayers = new ArrayList();

    public boolean contains(Player player) {
        return waitingPlayers.contains(player.getUniqueId());
    }

    public int getSize() {
        return waitingPlayers.size();
    }

    public Player get(int index) {
        return Bukkit.getPlayer(waitingPlayers.get(index));
    }

    public void add(Player player) {
        waitingPlayers.add(player.getUniqueId());
    }

    public void remove(Player player) {
        waitingPlayers.remove(player.getUniqueId());
    }

    public void shuffle() {
        Collections.shuffle(waitingPlayers);
    }
}
