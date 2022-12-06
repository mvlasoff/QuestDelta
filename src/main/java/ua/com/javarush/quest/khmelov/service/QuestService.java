package ua.com.javarush.quest.khmelov.service;

import lombok.AllArgsConstructor;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.QuestDto;
import ua.com.javarush.quest.khmelov.entity.Answer;
import ua.com.javarush.quest.khmelov.entity.GameState;
import ua.com.javarush.quest.khmelov.entity.Quest;
import ua.com.javarush.quest.khmelov.entity.Question;
import ua.com.javarush.quest.khmelov.exception.AppException;
import ua.com.javarush.quest.khmelov.mapping.Mapper;
import ua.com.javarush.quest.khmelov.repository.memory.AnswerRepository;
import ua.com.javarush.quest.khmelov.repository.memory.QuestRepository;
import ua.com.javarush.quest.khmelov.repository.memory.QuestionRepository;
import ua.com.javarush.quest.khmelov.repository.memory.UserRepository;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class QuestService {

    public static final String QUEST_SYMBOL = ":";
    public static final String WIN_SYMBOL = "+";
    public static final String LOST_SYMBOL = "-";
    public static final String LINK_SYMBOL = "<";
    public static final String DIGITS = "\\d+";

    private final UserRepository userRepository;
    private final QuestRepository questRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public Collection<QuestDto> getAll() {
        return questRepository.getAll()
                .map(Mapper.quest::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Optional<QuestDto> get(long id) {
        Quest quest = questRepository.get(id);
        return Mapper.quest.get(quest);
    }


    public Optional<QuestDto> create(FormData formData, Long userId) {
        String name = formData.getParameter(Jsp.Key.NAME);
        String text = formData.getParameter(Jsp.Key.TEXT);
        return create(name, text, userId);
    }

    public Optional<QuestDto> create(String name, String text, Long userId) {
        Map<Long, Question> map = fillDraftMap(text);
        if (map.size() < 1) {
            return Optional.empty();
        }
        Quest quest = Quest.with()
                .user(userRepository.get(userId))
                .name(name)
                .build();
        questRepository.create(quest);
        userRepository.get(userId).getQuests().add(quest);

        map.values().forEach(questionRepository::create);

        Long startKey = findStartQuestionLabel(text);
        Long startId = map.get(startKey).getId();
        quest.setStartQuestionId(startId);

        updateLinksAndId(map, quest);
        map.values().stream()
                .flatMap(q -> q.getAnswers().stream())
                .forEach(answerRepository::create);
        return Mapper.quest.get(quest);
    }

    private Long findStartQuestionLabel(String text) {
        Matcher matcher = Pattern.compile(DIGITS).matcher(text);
        if (matcher.find()) {
            return Long.parseLong(matcher.group());
        }
        throw new AppException("not found start index in text");
    }

    private Map<Long, Question> fillDraftMap(String text) {
        Map<Long, Question> map = new TreeMap<>();
        text = "\n" + text;
        String pattern = "\n(%s)([:<+-])".formatted(DIGITS);
        String[] parts = text.split(pattern);
        int index = 1;
        Matcher labelIterator = Pattern.compile(pattern).matcher(text);
        Question question = Question.with().build();
        while (labelIterator.find()) {
            long key = Long.parseLong(labelIterator.group(1));
            String type = labelIterator.group(2);
            String partText = parts[index++].strip();
            Optional<Question> newQuestion = fillQuestion(question, key, type, partText);
            if (newQuestion.isPresent()) {
                question = newQuestion.get();
                map.put(key, question);
            }
        }
        return map;
    }

    private Optional<Question> fillQuestion(Question currentQuestion, long key, String type, String partText) {
        currentQuestion = switch (type) {
            case QUEST_SYMBOL -> Question.with().text(partText).gameState(GameState.PLAY).build();
            case WIN_SYMBOL -> Question.with().text(partText).gameState(GameState.WIN).build();
            case LOST_SYMBOL -> Question.with().text(partText).gameState(GameState.LOST).build();
            case LINK_SYMBOL -> {
                Answer build = Answer.with()
                        .nextQuestionId(key)
                        .questionId(currentQuestion.getId())
                        .text(partText)
                        .build();
                currentQuestion.getAnswers().add(build);
                yield null;
            }
            default -> throw new AppException("incorrect parsing");
        };
        return Optional.ofNullable(currentQuestion);
    }

    private void updateLinksAndId(Map<Long, Question> map, Quest quest) {
        for (Question question : map.values()) {
            question.setQuest(quest);
            quest.getQuestions().add(question);
            for (Answer answer : question.getAnswers()) {
                answer.setQuestionId(question.getId());
                Long key = answer.getNextQuestionId(); //label (index in text)
                if (map.containsKey(key)) {
                    answer.setNextQuestionId(map.get(key).getId()); //real index
                }
            }
        }
    }
}
