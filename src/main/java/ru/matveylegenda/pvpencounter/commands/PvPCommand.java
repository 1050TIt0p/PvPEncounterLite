package ru.matveylegenda.pvpencounter.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.matveylegenda.pvpencounter.PvPEncounter;
import ru.matveylegenda.pvpencounter.gui.Menu;
import ru.matveylegenda.pvpencounter.utils.configs.MainConfig;
import ru.matveylegenda.pvpencounter.utils.configs.MenuConfig;
import ru.matveylegenda.pvpencounter.utils.configs.MessagesConfig;

import static ru.matveylegenda.pvpencounter.utils.ColorParser.colorize;

public class PvPCommand implements CommandExecutor {
    private MainConfig mainConfig = PvPEncounter.getInstance().mainConfig;
    private MenuConfig menuConfig = PvPEncounter.getInstance().menuConfig;
    private MessagesConfig messagesConfig = PvPEncounter.getInstance().messagesConfig;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length != 0 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("pvpencounter.reload")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.closeInventory();
            }

            mainConfig.reloadConfig();
            menuConfig.reloadConfig();
            messagesConfig.reloadConfig();

            sender.sendMessage(colorize(messagesConfig.reload));

            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Menu menu = new Menu();

            player.openInventory(menu.getInventory());

            return true;
        } else {
            sender.sendMessage("§cТолько игроки могут использовать эту команду!");

            return true;
        }
    }
}
