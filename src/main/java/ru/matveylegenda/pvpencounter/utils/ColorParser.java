package ru.matveylegenda.pvpencounter.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;

import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

public class ColorParser {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");

    public static String colorize(String content) {
        Matcher matcher = HEX_PATTERN.matcher(content);
        StringBuilder builder = new StringBuilder(content.length() + 4 * 8);

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

        content = matcher.appendTail(builder).toString();

        return ChatColor.translateAlternateColorCodes('&', content);
    }

    public static List<String> colorize(List<String> content) {
        List<String> messages = new ArrayList<>();
        for (String message : content) {
            messages.add(colorize(message));
        }

        return messages;
    }
}
