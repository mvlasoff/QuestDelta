package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.*;

public class SpaceQuest implements Quest {
    private final Question startQuestion;
    private final HashMap<Long, Question> questions;

    public SpaceQuest() {
        Question question01 = new Question("You lost memory. Are you going toward UFO?", false);
        Question question02 = new Question("Are you going to captain?", false);
        Question question03 = new Question("Captain is asking: -Who are you?", false);
        Question question04 = new Question("You refused. You lost.", true);
        Question question05 = new Question("You didn't go to captain. You lost.", true);
        Question question06 = new Question("I don't like pirates. You lost.", true);
        Question question07 = new Question("Finally we found you. You going back to Jedi's school.", true);

        questions = new HashMap<>();
        startQuestion = question01;

        questions.put(1L, question01);
        questions.put(2L, question02);
        questions.put(3L, question03);
        questions.put(4L, question04);
        questions.put(5L, question05);
        questions.put(6L, question06);
        questions.put(7L, question07);


        Answer answer01 = new Answer("I go toward UFO.", question02);
        Answer answer02 = new Answer("I don't go toward UFO.", question04);
        Answer answer03 = new Answer("I'm going to captain.", question03);
        Answer answer04 = new Answer("I'm not going to captain.", question05);
        Answer answer05 = new Answer("Tell truth. I'm a padawan Vasilij Pupkin.", question07);
        Answer answer06 = new Answer("Lie. I'm Jabba.", question06);
        Answer answer07 = new Answer("Show your driver license and tell you are now captain of the ship.", question01);

        question01.addAnswers(answer01, answer02);
        question02.addAnswers(answer03, answer04);
        question03.addAnswers(answer05, answer06, answer07);
    }

    public Question getStartQuestion() {
        return startQuestion;
    }

    public Collection<Question> getAll() {
        return questions.values();
    }

    public Optional<Question> get(long id) {
        return Optional.ofNullable(questions.get(id));
    }
}
