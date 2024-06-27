package ru.matveylegenda.pvpencounter;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.matveylegenda.pvpencounter.commands.PvPCommand;
import ru.matveylegenda.pvpencounter.listeners.MenuListener;
import ru.matveylegenda.pvpencounter.utils.Metrics;
import ru.matveylegenda.pvpencounter.utils.configs.MainConfig;
import ru.matveylegenda.pvpencounter.utils.configs.MenuConfig;
import ru.matveylegenda.pvpencounter.utils.configs.MessagesConfig;

import java.io.File;

public final class PvPEncounter extends JavaPlugin {
    private static PvPEncounter instance;

    public File mainConfigFile = new File(getDataFolder() + "/config.yml");
    public MainConfig mainConfig = new MainConfig();

    public File menuConfigFile = new File(getDataFolder() + "/menu.yml");
    public MenuConfig menuConfig = new MenuConfig();

    public File messagesConfigFile = new File(getDataFolder() + "/messages.yml");
    public MessagesConfig messagesConfig = new MessagesConfig();

    private final ConsoleCommandSender consoleSender = getServer().getConsoleSender();

    @Override
    public void onEnable() {
        instance = this;

        mainConfig.reloadConfig();
        menuConfig.reloadConfig();
        messagesConfig.reloadConfig();

        consoleSender.sendMessage("§b  _____        _____  ______                             _            ");
        consoleSender.sendMessage("§b |  __ \\      |  __ \\|  ____|                           | |           ");
        consoleSender.sendMessage("§b | |__) |_   _| |__) | |__   _ __   ___ ___  _   _ _ __ | |_ ___ _ __ ");
        consoleSender.sendMessage("§b |  ___/\\ \\ / /  ___/|  __| | '_ \\ / __/ _ \\| | | | '_ \\| __/ _ \\ '__|");
        consoleSender.sendMessage("§b | |     \\ V /| |    | |____| | | | (_| (_) | |_| | | | | ||  __/ |   ");
        consoleSender.sendMessage("§b |_|      \\_/ |_|    |______|_| |_|\\___\\___/ \\__,_|_| |_|\\__\\___|_|   ");
        consoleSender.sendMessage("§9 PvPEncounterLite " + getDescription().getVersion() + " §8| §9" + Bukkit.getVersion());

        getCommand("pvp").setExecutor(new PvPCommand());

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        int pluginId = 21093;
        Metrics metrics = new Metrics(this, pluginId);
    }

    public static PvPEncounter getInstance() {
        return instance;
    }
}
