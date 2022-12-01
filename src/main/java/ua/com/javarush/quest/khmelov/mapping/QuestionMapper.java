package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.QuestionDto;
import ua.com.javarush.quest.khmelov.entity.Question;

import java.util.Optional;

/**
 * Class package-private. Use only <code>interface Mapper</code>
 */
class QuestionMapper implements Mapper<Question, QuestionDto> {

    @Override
    public Optional<QuestionDto> get(Question question) {
        return question != null
                ? Optional.of(QuestionDto.with()
                .id(question.getId())
                .questId(question.getQuest().getId())
                .image(question.getImage())
                .text(question.getText())
                .answers(question.getAnswers().stream()
                        .map(Mapper.answer::get)
                        .map(Optional::orElseThrow)
                        .toList()
                )
                .build()
        ) : Optional.empty();
    }

    @Override
    public Question parse(FormData formData) {
        Question quest = Question.with().build();
        return fill(quest, formData);
    }
}
