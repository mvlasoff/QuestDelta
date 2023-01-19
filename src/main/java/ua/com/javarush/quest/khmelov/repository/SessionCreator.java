package ua.com.javarush.quest.khmelov.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.javarush.quest.khmelov.entity.*;

public class SessionCreator implements AutoCloseable {

    protected final SessionFactory sessionFactory;

    public SessionCreator() {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = getSessionFactory(configuration);
    }

    protected SessionCreator(Configuration configuration) {
        sessionFactory = getSessionFactory(configuration);
    }

    private SessionFactory getSessionFactory(Configuration configuration) {
        LiquibaseChecker.check(configuration);
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(Answer.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Quest.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserInfo.class);
        return configuration.buildSessionFactory();
    }

    public Session get() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
