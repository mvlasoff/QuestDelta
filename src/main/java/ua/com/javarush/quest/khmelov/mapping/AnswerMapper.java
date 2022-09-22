package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.dto.ui.AnswerDto;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.entity.Answer;

import java.util.Optional;

/**
 * Class package-private. Use only <code>interface Mapper</code>
 */
class AnswerMapper implements Mapper<Answer, AnswerDto> {

    @Override
    public Optional<AnswerDto> get(Answer answer) {
        return answer != null
                ? Optional.of(AnswerDto.with()
                .id(answer.getId())
                .text(answer.getText())
                .nextQuestionId(answer.getNextQuestionId())
                .build()
        ) : Optional.empty();
    }

    @Override
    public Answer parse(FormData formData) {
        Answer quest = Answer.with().build();
        return fill(quest, formData);
    }
}
