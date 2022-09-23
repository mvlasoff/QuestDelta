package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.*;

public class SpaceQuest implements Quest {
    private final Question startQuestion;
    private final HashMap<Long, Question> questions;

    public SpaceQuest() {
        Question question01 = new Question(11L, "You lost memory. Do you go toward UFO?", false);
        Question question02 = new Question(12L,"Are you going to captain?", false);
        Question question03 = new Question(13L,"Captain is asking: -Who are you?", false);
        Question question04 = new Question(14L, "You refused. You lost.", true);
        Question question05 = new Question(15L,"You didn't go to captain. You lost.", true);
        Question question06 = new Question(16L,"I don't like pirates. You lost.", true);
        Question question07 = new Question(17L, "Finally we found you. You going back to Jedi's school. You won!", true);

        questions = new HashMap<>();
        startQuestion = question01;

        questions.put(question01.getId(), question01);
        questions.put(question02.getId(), question02);
        questions.put(question03.getId(), question03);
        questions.put(question04.getId(), question04);
        questions.put(question05.getId(), question05);
        questions.put(question06.getId(), question06);
        questions.put(question07.getId(), question07);


        Answer answer01 = new Answer("Go toward UFO.", question02);
        Answer answer02 = new Answer("Don't go toward UFO.", question04);
        Answer answer03 = new Answer("I'm going to captain.", question03);
        Answer answer04 = new Answer("I'm not going to captain.", question05);
        Answer answer05 = new Answer("Tell the truth. I'm a padawan Chuck Waggon.", question07);
        Answer answer06 = new Answer("Lie. I'm Jabba.", question06);
        Answer answer07 = new Answer("Show your driver license and tell you are now captain of the ship.", question01);

        question01.addAnswers(answer01, answer02);
        question02.addAnswers(answer03, answer04);
        question03.addAnswers(answer05, answer06, answer07);
    }

    public Question getStartQuestion() {
        return startQuestion;
    }

    public Collection<Answer> getAnswers(Question question) {
        return question.getAnswersSet();
    }

    public Collection<Question> getAll() {
        return questions.values();
    }

    public Optional<Question> get(long id) {
        return Optional.ofNullable(questions.get(id));
    }
}
