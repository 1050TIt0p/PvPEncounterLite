package ru.matveylegenda.pvpencounter.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class WaitingList {
    private static ArrayList<Player> waitingPlayers = new ArrayList();

    public boolean contains(Player player) {
        return waitingPlayers.contains(player);
    }

    public int getSize() {
        return waitingPlayers.size();
    }

    public Player get(int index) {
        return waitingPlayers.get(index);
    }

    public void add(Player player) {
        waitingPlayers.add(player);
    }

    public void remove(Player player) {
        waitingPlayers.remove(player);
    }

    public void shuffle() {
        Collections.shuffle(waitingPlayers);
    }
}
