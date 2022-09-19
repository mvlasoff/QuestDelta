package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.QuestDto;
import ua.com.javarush.quest.khmelov.entity.Quest;

import java.util.Optional;

/**
 * Class package-private. Use only <code>interface Mapper</code>
 */
class QuestMapper implements Mapper<Quest, QuestDto> {

    @Override
    public Optional<QuestDto> write(Quest quest) {
        return quest != null
                ? Optional.of(QuestDto.with()
                .id(quest.getId())
                .name(quest.getName())
                .authorId(quest.getAuthorId())
                .build()
        ) : Optional.empty();
    }

    @Override
    public Quest read(FormData formData) {
        Quest quest = Quest.with().build();
        return fill(quest, formData);
    }
}
