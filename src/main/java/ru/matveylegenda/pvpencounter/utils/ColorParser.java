package ru.matveylegenda.pvpencounter.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;

import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

public class ColorParser {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");

    public static String colorize(String message, String serializer) {
        switch (serializer) {
            case "LEGACY": {
                Matcher matcher = HEX_PATTERN.matcher(message);
                StringBuilder builder = new StringBuilder(message.length() + 4 * 8);
                while (matcher.find()) {
                    String group = matcher.group(1);
                    matcher.appendReplacement(builder,
                            COLOR_CHAR + "x" +
                                    COLOR_CHAR + group.charAt(0) +
                                    COLOR_CHAR + group.charAt(1) +
                                    COLOR_CHAR + group.charAt(2) +
                                    COLOR_CHAR + group.charAt(3) +
                                    COLOR_CHAR + group.charAt(4) +
                                    COLOR_CHAR + group.charAt(5));
                }
                message = matcher.appendTail(builder).toString();

                return ChatColor.translateAlternateColorCodes('&', message);
            }

            case "MINIMESSAGE": {
                Component component = MiniMessage.miniMessage().deserialize(message);

                return LegacyComponentSerializer.legacySection().serialize(component);
            }

            default: {
                return message;
            }
        }
    }

    public static List<String> colorize(List<String> content, String serializer) {
        List<String> messages = new ArrayList<>();
        for (String message : content) {
            messages.add(colorize(message, serializer));
        }

        return messages;
    }
}
