package ua.com.javarush.quest.khmelov.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.javarush.quest.khmelov.entity.*;

public class SessionCreator implements AutoCloseable {

    private final SessionFactory sessionFactory;

    public SessionCreator() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(Answer.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Quest.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserInfo.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session open() {
        return sessionFactory.openSession();
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
