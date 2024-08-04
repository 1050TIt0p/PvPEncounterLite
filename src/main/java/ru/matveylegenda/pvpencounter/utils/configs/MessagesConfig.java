package ru.matveylegenda.pvpencounter.utils.configs;

import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.annotations.NewLine;
import net.elytrium.serializer.language.object.YamlSerializable;
import ru.matveylegenda.pvpencounter.PvPEncounter;

public class MessagesConfig extends YamlSerializable {

    @Comment({
            @CommentValue(" Сообщение отправляемое при входе в очередь")
    })
    public String joinQueue = "&fВы добавлены в очередь";

    @Comment({
            @CommentValue(" Сообщение отправляемое при входе в очередь если игрок уже состоит в ней")
    })
    public String alreadyQueue = "&fВы уже в очереди";

    @Comment({
            @CommentValue(" Сообщение отправляемое при выходе из очереди")
    })
    public String quitQueue = "&fВы вышли из очереди";

    @Comment({
            @CommentValue(" Сообщение отправляемое при выходе из очереди если игрок в ней нет")
    })
    public String notQueue = "&fВы не состоите в очереди";

    @Comment({
            @CommentValue(" Сообщение отправляемое при телепортации")
    })
    public String teleportPlayer = "&fИгрок &#8833EC{player} &fнайден! Телепортация...";

    @Comment({
            @CommentValue(" Сообщение отправляемое если локация для телепортации не найдена")
    })
    public String locationNotFound = "&fЛокация не найдена! Телепортация отменена";

    @NewLine(amount = 1)
    @Comment({
            @CommentValue(" Сообщение отправляемое при перезагруке плагина")
    })
    public String reload = "&fПлагин перезагружен";

    @NewLine(amount = 1)
    @Comment({
            @CommentValue(" Сообщение отправляемое всем чат при входе в очередь"),
            @CommentValue(" Включается/выключается в config.yml")
    })
    public String joinBroadcastMessage = "&#8833EC{player} &fожидает второго игрока в &#8833EC/pvp";

    public void reloadConfig() {
        reload(PvPEncounter.getInstance().messagesConfigFile.toPath());
    }
}
