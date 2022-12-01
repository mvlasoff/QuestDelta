package ua.com.javarush.quest.khmelov.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.javarush.quest.khmelov.entity.Answer;
import ua.com.javarush.quest.khmelov.entity.Quest;
import ua.com.javarush.quest.khmelov.entity.Question;
import ua.com.javarush.quest.khmelov.entity.User;

public class SessionCreator implements AutoCloseable{

    private final SessionFactory sessionFactory;

    public SessionCreator() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Quest.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Answer.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session open(){
        return sessionFactory.openSession();
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
