package ru.matveylegenda.pvpencounter.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import ru.matveylegenda.pvpencounter.PvPEncounter;
import ru.matveylegenda.pvpencounter.utils.configs.MainConfig;

import java.util.List;
import java.util.Random;

public class TeleportUtil {
    private MainConfig mainConfig = PvPEncounter.getInstance().mainConfig;

    public Location getRandomLocation(World world, int minX, int maxX, int minZ, int maxZ) {
        Random random = new Random();
        int attempts = 0;

        while (attempts < mainConfig.randomLocation.attempts) {
            int x = minX + random.nextInt(maxX - minX + 1);
            int z = minZ + random.nextInt(maxZ - minZ + 1);
            int y = world.getHighestBlockYAt(x, z);

            if (isSafeLocation(world.getBlockAt(x, y, z).getType())) {
                return new Location(world, x + 0.5, y + 1, z + 0.5);
            }

            attempts++;
        }

        return null;
    }

    private boolean isSafeLocation(Material block) {
        List<String> blackListBlocks = mainConfig.randomLocation.blackListBlocks;

        return block.isSolid() && !blackListBlocks.contains(block.toString());
    }
}
