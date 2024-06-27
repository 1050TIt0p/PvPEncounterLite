package ru.matveylegenda.pvpencounter.utils.configs;

import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.annotations.NewLine;
import net.elytrium.serializer.language.object.YamlSerializable;
import ru.matveylegenda.pvpencounter.PvPEncounter;

import java.util.Arrays;
import java.util.List;

public class MainConfig extends YamlSerializable {

    public RandomLocation randomLocation = new RandomLocation();

    @Comment({
            @CommentValue(" Настроки случайной телепортации")
    })
    public static class RandomLocation {

        @Comment({
                @CommentValue(" Количество попыток для телепортации")
        })
        public int attempts = 100;

        @NewLine(amount = 1)
        @Comment({
                @CommentValue(" Название мира")
        })
        public String world = "world";

        @Comment({
                @CommentValue(" Минимальная X координата для рандмной телепортации")
        })
        public int minX = -1000;

        @Comment({
                @CommentValue(" Максимальная X координата для рандмной телепортации")
        })
        public int maxX = 1000;

        @Comment({
                @CommentValue(" Минимальная Z координата для рандмной телепортации")
        })
        public int minZ = -1000;

        @Comment({
                @CommentValue(" Максимальная Z координата для рандмной телепортации")
        })
        public int maxZ = 1000;

        @NewLine(amount = 1)
        @Comment({
                @CommentValue(" Блоки на которые не будет телепортировать")
        })
        public List<String> blackListBlocks = Arrays.asList(
                "AIR",
                "LAVA",
                "WATER"
        );
    }

    @NewLine(amount = 1)
    @Comment({
            @CommentValue(" Включена ли отправка сообщения всем игрокам о входе в очередь"),
            @CommentValue(" Сообщение редактируется в messages.yml")
    })
    public boolean joinBroadcastMessageEnabled = true;

    public void reloadConfig() {
        reload(PvPEncounter.getInstance().mainConfigFile.toPath());
    }
}
