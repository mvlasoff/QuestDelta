package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.repository.Container;
import ua.com.javarush.quest.khmelov.repository.SessionCreator;

public class BaseEntityTest {
    protected static Session session;
    protected static SessionCreator sessionCreator;

    @BeforeAll
    static void setUp() {
        Container.init();
        sessionCreator = Winter.getBean(SessionCreator.class);
        session = sessionCreator.get();
    }

}
