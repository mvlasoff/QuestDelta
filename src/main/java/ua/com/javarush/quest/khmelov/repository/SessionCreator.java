package ua.com.javarush.quest.khmelov.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.com.javarush.quest.khmelov.entity.*;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCreator implements AutoCloseable {

    private final SessionFactory sessionFactory;
    private final AtomicInteger level = new AtomicInteger();
    private volatile Transaction transaction;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public SessionCreator() {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = getSessionFactory(configuration);
    }

    protected SessionCreator(Configuration configuration) {
        sessionFactory = getSessionFactory(configuration);
    }

    private SessionFactory getSessionFactory(Configuration configuration) {
        LiquibaseChecker.updateDataBase(configuration);
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

    public Transaction startTransactional() {
        if (level.getAndIncrement() == 0) {
            Session session = get();
            transaction = session.beginTransaction();
            Transaction sessionTransaction = session.getTransaction();
        }
        return transaction;
    }

    public void endTransactional() {

        if (level.decrementAndGet() == 0) {
            try {
                transaction.commit();
            } catch (RuntimeException e) {
                transaction.rollback();
                throw e;
            }
        } else {
            get().flush();
        }
    }
}
