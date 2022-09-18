package ua.com.javarush.quest.khmelov.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.entity.*;
import ua.com.javarush.quest.khmelov.repository.AnswerRepository;
import ua.com.javarush.quest.khmelov.repository.QuestRepository;
import ua.com.javarush.quest.khmelov.repository.QuestionRepository;
import ua.com.javarush.quest.khmelov.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RepositoryLoader {

    private final UserRepository userRepository = UserRepository.get();
    private final QuestRepository questRepository = QuestRepository.get();
    private final QuestionRepository questionRepository = QuestionRepository.get();
    private final AnswerRepository answerRepository = AnswerRepository.get();

    public void load() {
        //load from json
        defaultInit();
        save();
    }

    public void save() {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            List<User> users = userRepository.getAll().toList();
            jsonMapper.writeValue(new File("tree.json"), users);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        Quest quest1 = Quest.with().authorId(ivan.getId()).name("Квест из задания").build();
        Quest quest2 = Quest.with().authorId(ivan.getId()).name("Проверим ваши знания арифметики").build();
        ArrayList<Quest> quests = new ArrayList<>();
        quests.add(quest1);
        quests.add(quest2);
        questRepository.create(quest1);
        questRepository.create(quest2);
        ivan.setQuests(quests);

        //вопросы
        Question question1 = Question.with().questId(quest2.getId())
                .text("Сколько будет дважды два").build();
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questionRepository.create(question1);
        quest2.setQuestions(questions);

        //ответы
        answerRepository.create(Answer.with().text("Один")
                .questionId(question1.getId()).correct(false).build());
        answerRepository.create(Answer.with().text("Два")
                .questionId(question1.getId()).correct(false).build());
        answerRepository.create(Answer.with().text("Три")
                .questionId(question1.getId()).correct(false).build());
        answerRepository.create(Answer.with().text("Четыре")
                .questionId(question1.getId()).correct(true).build());
        question1.setAnswers(answerRepository.find(Answer.with()
                .questionId(question1.getId()).build()).toList()
        );

    }
}
