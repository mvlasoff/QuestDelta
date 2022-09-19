package ua.com.javarush.quest.khmelov.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.config.ConfigLoader;
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

    public String DATABASE_PATH = "db";

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
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<User> users = userRepository.getAll().toList();
        Path databasePath = ConfigLoader.getDatabasePath();
        Files.createDirectories(databasePath);
        try (OutputStream outputStream = Files.newOutputStream(databasePath.resolve("tree.json"))) {
            jsonMapper.writeValue(outputStream, users);
        }
    }

    private void defaultInit() {
        //пользователи
        User ivan = User.with().id(1L).login("Ivan").password("456").role(Role.ADMIN).build();
        User andrew = User.with().id(2L).login("Andrew").password("789").role(Role.GUEST).build();
        User elena = User.with().id(3L).login("Elena").password("123").role(Role.USER).build();
        userRepository.create(ivan);
        userRepository.create(andrew);
        userRepository.create(elena);

        //квесты
        Quest quest1 = Quest.with().id(4L).authorId(ivan.getId())
                .name("Квест из задания").build();
        Quest quest2 = Quest.with().id(5L).authorId(ivan.getId())
                .name("Проверим ваши знания арифметики").build();
        ArrayList<Quest> quests = new ArrayList<>();
        quests.add(quest1);
        quests.add(quest2);
        questRepository.create(quest1);
        questRepository.create(quest2);
        ivan.setQuests(quests);

        //вопросы
        Question question1 = Question.with().id(51L).questId(quest2.getId())
                .text("Сколько будет дважды два").build();
        Question lost = Question.with().id(52L).questId(quest2.getId())
                .text("Вы проиграли :`(").build();
        Question win = Question.with().id(53L).questId(quest2.getId())
                .text("Ура! Вы выиграли! :D").build();
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questionRepository.create(question1);
        quest2.setQuestions(questions);

        //ответы
        answerRepository.create(Answer.with().text("Один").nextQuestionId(lost.getId())
                .questionId(question1.getId()).build());
        answerRepository.create(Answer.with().text("Два").nextQuestionId(lost.getId())
                .questionId(question1.getId()).build());
        answerRepository.create(Answer.with().text("Три").nextQuestionId(lost.getId())
                .questionId(question1.getId()).build());
        answerRepository.create(Answer.with().text("Четыре").nextQuestionId(win.getId())
                .questionId(question1.getId()).build());
        question1.setAnswers(answerRepository.find(Answer.with()
                .questionId(question1.getId()).build()).toList()
        );

    }
}
