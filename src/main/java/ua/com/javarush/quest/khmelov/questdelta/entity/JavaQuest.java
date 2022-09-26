package ua.com.javarush.quest.khmelov.questdelta.entity;

import jakarta.servlet.annotation.WebServlet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class JavaQuest implements Quest {
    private final String questName = "Java Quest";
    private final Question startQuestion;
    private final HashMap<Long, Question> questions;

    public JavaQuest() {
        Question question01 = new Question(21L, "What is the output of the following Java program?", "/images/java01.png");
        Question question02 = new Question(22L, "How many types of constructors are used in Java?", null);
        Question question03 = new Question(23L, "Does constructor return any value?", null);
        Question question04 = new Question(24L, "What is the output of the following Java program?", "/images/java04.png");
        Question question05 = new Question(25L, "What is the output of the following Java program?", "/images/java05.png");
        Question question06 = new Question(26L, "What is the output of the following Java program?", "/images/java06.png");
        Question question07 = new Question(27L, "Can we override the static methods?", null);
        Question question08 = new Question(28L, "Can we execute a program without main() method?", null);
        Question question09 = new Question(29L, "What if the static modifier is removed from the signature of the main() method?", null);
        Question question10 = new Question(30L, "Can we assign the reference to 'this' variable?", "/images/java10.png");
        Question question11 = new Question(31L, "You lost.", null);
        Question question12 = new Question(32L, "You win!", null);

        questions = new HashMap<>();
        startQuestion = question01;

        questions.put(question01.getId(), question01);
        questions.put(question02.getId(), question02);
        questions.put(question03.getId(), question03);
        questions.put(question04.getId(), question04);
        questions.put(question05.getId(), question05);
        questions.put(question06.getId(), question06);
        questions.put(question07.getId(), question07);
        questions.put(question08.getId(), question08);
        questions.put(question09.getId(), question09);
        questions.put(question10.getId(), question10);
        questions.put(question11.getId(), question11);
        questions.put(question12.getId(), question12);

        Answer answer01 = new Answer("Hello Javatpoint", question11);
        Answer answer02 = new Answer("Exception.", question11);
        Answer answer03 = new Answer("Compile error.", question02);

        Answer answer04 = new Answer("One.", question11);
        Answer answer05 = new Answer("Two.", question03);
        Answer answer06 = new Answer("Three.", question11);

        Answer answer07 = new Answer("Yes.", question04);
        Answer answer08 = new Answer("No.", question11);
        Answer answer09 = new Answer("It depends on class type.", question11);

        Answer answer10 = new Answer("a = 10 b = 15.0", question11);
        Answer answer11 = new Answer("Exception.", question11);
        Answer answer12 = new Answer("a = 10 b = 15", question05);

        Answer answer13 = new Answer("0", question06);
        Answer answer14 = new Answer("Exception.", question11);
        Answer answer15 = new Answer("Compile Error.", question11);

        Answer answer16 = new Answer("0 0", question11);
        Answer answer17 = new Answer("Exception.", question11);
        Answer answer18 = new Answer("Compile Error.", question07);

        Answer answer19 = new Answer("Yes.", question11);
        Answer answer20 = new Answer("No.", question08);
        Answer answer21 = new Answer("Yes, if it's public.", question11);

        Answer answer22 = new Answer("Yes.", question11);
        Answer answer23 = new Answer("No.", question11);
        Answer answer24 = new Answer("Yes. It was possible before JDK 1.7.", question09);

        Answer answer25 = new Answer("Nothing happens.", question11);
        Answer answer26 = new Answer("Exception.", question10);
        Answer answer27 = new Answer("Compile error.", question11);

        Answer answer28 = new Answer("Yes.", question11);
        Answer answer29 = new Answer("Exception.", question11);
        Answer answer30 = new Answer("Compile error.", question12);

        question01.addAnswers(answer01, answer02, answer03);
        question02.addAnswers(answer04, answer05, answer06);
        question03.addAnswers(answer07, answer08, answer09);
        question04.addAnswers(answer10, answer11, answer12);
        question05.addAnswers(answer13, answer14, answer15);
        question06.addAnswers(answer16, answer17, answer18);
        question07.addAnswers(answer19, answer20, answer21);
        question08.addAnswers(answer22, answer23, answer24);
        question09.addAnswers(answer25, answer26, answer27);
        question10.addAnswers(answer28, answer29, answer30);
    }


    @Override
    public String getQuestName() {
        return questName;
    }

    @Override
    public Question getStartQuestion() {
        return startQuestion;
    }

    @Override
    public Collection<Question> getAll() {
        return questions.values();
    }

    @Override
    public Optional<Question> get(long id) {
        return Optional.ofNullable(questions.get(id));
    }

    @Override
    public Collection<Answer> getAnswers(Question question) {
        return question.getAnswersSet();
    }
}
