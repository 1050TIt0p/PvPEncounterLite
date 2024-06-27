package ru.matveylegenda.pvpencounter.utils.configs;

import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.annotations.NewLine;
import net.elytrium.serializer.language.object.YamlSerializable;
import ru.matveylegenda.pvpencounter.PvPEncounter;

import java.util.Arrays;
import java.util.List;

public class MenuConfig extends YamlSerializable {

    @Comment({
            @CommentValue(" Заголовок меню")
    })
    public String title = "&8PvPEncounter";

    @Comment({
            @CommentValue(" Количество слотов в меню")
    })
    public int size = 45;

    public Items items = new Items();

    @NewLine(amount = 1)
    public static class Items {

        public Join join = new Join();

        @Comment({
                @CommentValue(" Настройка предмета для входа в очередь")
        })
        public static class Join {

            @Comment({
                    @CommentValue(" Материал предмета")
            })
            public String material = "NETHERITE_SWORD";

            @Comment({
                    @CommentValue(" Слот предмета")
            })
            public int slot = 21;

            @Comment({
                    @CommentValue(" Название предмета")
            })
            public String name = " ";

            @Comment({
                    @CommentValue(" Описание предмета")
            })
            public List<String> lore = Arrays.asList(
                    " &#2ffbb8Войти в очередь ",
                    "",
                    " &fНажимая сюда, вы входите в очередь ",
                    " &fигроков, которые хотят PvP. ",
                    " &fПосле того как в очередь зайдет ",
                    " &fвторой игрок, одного из вас ",
                    " &fтелепортирует к другому! ",
                    "",
                    " &#46fbf6| Нажмите, чтобы войти в очередь.",
                    ""
            );
        }

        public Quit quit = new Quit();

        @NewLine(amount = 1)
        @Comment({
                @CommentValue(" Настройка предмета для выхода из очереди")
        })
        public static class Quit {

            @Comment({
                    @CommentValue(" Материал предмета")
            })
            public String material = "OAK_DOOR";

            @Comment({
                    @CommentValue(" Слот предмета")
            })
            public int slot = 23;

            @Comment({
                    @CommentValue(" Название предмета")
            })
            public String name = " ";

            @Comment({
                    @CommentValue(" Описание предмета")
            })
            public List<String> lore = Arrays.asList(
                    " &#2ffbb8Выйти из очереди ",
                    "",
                    " &#46fbf6| Нажмите, чтобы выйти из очереди. ",
                    ""
            );
        }
    }

    public void reloadConfig() {
        reload(PvPEncounter.getInstance().menuConfigFile.toPath());
    }
}
