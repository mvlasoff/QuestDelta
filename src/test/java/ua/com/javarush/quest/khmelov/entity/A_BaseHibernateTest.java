package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.repository.hibernate.SessionCreator;

public abstract class A_BaseHibernateTest {
    protected static SessionCreator sessionCreator;
    protected static Session session;

    @BeforeAll
    static void setUp() {
        SessionCreator sessionCreator = Winter.getBean(SessionCreator.class);
        session = sessionCreator.open();
    }


    @AfterAll
    static void tearDown() {
        session.close();
    }
}
