package ua.com.javarush.quest.khmelov.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.AnswerDto;
import ua.com.javarush.quest.khmelov.dto.ui.QuestionDto;
import ua.com.javarush.quest.khmelov.entity.Question;
import ua.com.javarush.quest.khmelov.mapping.Mapper;
import ua.com.javarush.quest.khmelov.repository.impl.QuestionRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Optional<QuestionDto> get(long id) {
        questionRepository.beginTransactional();
        try {
            Question question = questionRepository.get(id);
            //here can be service for answers, but it found in entity
            List<AnswerDto> answersDto = question.getAnswers().stream()
                    .map(Mapper.answer::get)
                    .map(Optional::orElseThrow)
                    .toList();
            Optional<QuestionDto> questionDto = Mapper.question.get(question);
            questionDto.orElseThrow().setAnswers(answersDto);
            return questionDto;
        } finally {
            questionRepository.endTransactional();
        }
    }

    @SneakyThrows
    public Optional<QuestionDto> update(FormData formData) {
        questionRepository.beginTransactional();
        try {
            Question question = questionRepository.get(formData.getId());
            Mapper.question.fill(question, formData);
            questionRepository.update(question);
            return Mapper.question.get(question);
        } finally {
            questionRepository.endTransactional();
        }
    }
}
