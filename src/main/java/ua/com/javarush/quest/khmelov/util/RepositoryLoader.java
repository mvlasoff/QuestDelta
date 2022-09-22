package ua.com.javarush.quest.khmelov.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.config.Config;
import ua.com.javarush.quest.khmelov.entity.*;
import ua.com.javarush.quest.khmelov.repository.AnswerRepository;
import ua.com.javarush.quest.khmelov.repository.QuestRepository;
import ua.com.javarush.quest.khmelov.repository.QuestionRepository;
import ua.com.javarush.quest.khmelov.repository.UserRepository;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RepositoryLoader {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    private final UserRepository userRepository = UserRepository.get();
    private final QuestRepository questRepository = QuestRepository.get();
    private final QuestionRepository questionRepository = QuestionRepository.get();
    private final AnswerRepository answerRepository = AnswerRepository.get();

    public void load() {
        //load from json
        defaultInit();
        save();
    }


    public static void main(String[] args) {
        RepositoryLoader.load();
    }


    @SneakyThrows
    public void save() {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        List<User> users = userRepository.getAll().toList();
        Path folder = Path.of(Config.DB_FOLDER.get());
        String name = Config.DB_JSON.get();
        Files.createDirectories(folder);
        try (OutputStream outputStream = Files.newOutputStream(folder.resolve(name))) {
            MAPPER.writeValue(outputStream, users);
        }
    }

    private void defaultInit() {
        //пользователи
        User ivan = new User(1L,"Ivan","456",Role.ADMIN);
        User andrew = new User(2L,"Andrew","789",Role.GUEST);
        User elena = new User(3L,"Elena","123",Role.USER);
        userRepository.create(ivan);
        userRepository.create(andrew);
        userRepository.create(elena);

        //квест JR
        Quest javarush = Quest.with()
                .id(4L)
                .authorId(ivan.getId())
                .name("Квест из задания")
                .build();
        questRepository.create(javarush);
        ivan.getQuests().add(javarush);

        //заглушка вопроса (сразу конец квеста)
        Question dummy = Question.with()
                .questId(javarush.getId())
                .text("Одношаговый квест-заглушка")
                .state(GameState.WIN)
                .build();
        questionRepository.create(dummy);
        javarush.setStartQuestionId(dummy.getId());

        //Математический квест
        Quest mathQuest = Quest.with()
                .id(5L)
                .authorId(ivan.getId())
                .name("Проверим ваши знания арифметики")
                .build();

        questRepository.create(mathQuest);
        ivan.getQuests().add(mathQuest);

        //вопросы
        Question doYouKnowMath = Question.with()
                .id(50L)
                .questId(mathQuest.getId())
                .text("Вы знаете арифметику?")
                .state(GameState.PLAY)
                .build();
        mathQuest.setStartQuestionId(doYouKnowMath.getId());
        Question howBeTwoMulTwo = Question.with()
                .id(51L)
                .questId(mathQuest.getId())
                .text("Сколько будет дважды два")
                .state(GameState.PLAY)
                .build();
        Question lost = Question.with()
                .id(52L)
                .questId(mathQuest.getId())
                .text("Вы проиграли :`(")
                .state(GameState.LOST)
                .build();
        Question win = Question.with().id(53L)
                .questId(mathQuest.getId())
                .text("Ура! Вы выиграли! :D")
                .state(GameState.WIN)
                .build();
        //добавим в репозитарий
        questionRepository.create(doYouKnowMath);
        questionRepository.create(howBeTwoMulTwo);
        questionRepository.create(win);
        questionRepository.create(lost);

        //а также в дерево зависимостей в сущностях
        mathQuest.getQuestions().add(doYouKnowMath);
        mathQuest.getQuestions().add(howBeTwoMulTwo);
        mathQuest.getQuestions().add(win);
        mathQuest.getQuestions().add(lost);

        //ответы
        a21(doYouKnowMath, lost, howBeTwoMulTwo);
        a22(howBeTwoMulTwo, lost, win);
    }

    private static void a21(Question q, Question lost, Question next) {
        answerRepository.create(Answer.with()
                .text("Конечно знаю")
                .nextQuestionId(next.getId())
                .questionId(q.getId())
                .build());
        answerRepository.create(Answer.with()
                .text("А что это такое?")
                .nextQuestionId(lost.getId())
                .questionId(q.getId())
                .build());
        answerRepository.find(Answer.with().questionId(q.getId()).build())
                .forEach(q.getAnswers()::add);
    }

    private static void a22(Question q, Question lost, Question win) {
        answerRepository.create(Answer.with()
                .text("Один")
                .nextQuestionId(lost.getId())
                .questionId(q.getId())
                .build());
        answerRepository.create(Answer.with()
                .text("Два")
                .nextQuestionId(lost.getId())
                .questionId(q.getId())
                .build());
        answerRepository.create(Answer.with()
                .text("Три")
                .nextQuestionId(lost.getId())
                .questionId(q.getId())
                .build());
        answerRepository.create(Answer.with()
                .text("Четыре")
                .nextQuestionId(win.getId())
                .questionId(q.getId())
                .build());
        answerRepository.find(Answer.with().questionId(q.getId()).build())
                .forEach(q.getAnswers()::add);
    }
}
