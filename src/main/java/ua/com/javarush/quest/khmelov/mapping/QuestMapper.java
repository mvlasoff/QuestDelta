package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.QuestDto;
import ua.com.javarush.quest.khmelov.entity.Quest;

import java.util.Optional;

/**
 * Class package-private. Use only <code>interface Mapper</code>
 */
class QuestMapper implements Mapper<Quest, QuestDto> {

    @Override
    public Optional<QuestDto> get(Quest quest) {
        return quest != null
                ? Optional.of(QuestDto.with()
                .id(quest.getId())
                .name(quest.getName())
                .userId(quest.getUser().getId())
                .questions(quest.getQuestions().stream()
                        .map(Mapper.question::get)
                        .map(Optional::orElseThrow)
                        .toList())
                .build()
        ) : Optional.empty();
    }

    @Override
    public Quest parse(FormData formData) {
        Quest quest = Quest.with().build();
        return fill(quest, formData);
    }
}
