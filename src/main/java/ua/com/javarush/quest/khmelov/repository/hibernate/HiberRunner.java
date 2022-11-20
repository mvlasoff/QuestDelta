package ua.com.javarush.quest.khmelov.repository.hibernate;

import org.hibernate.Session;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.User;

public class HiberRunner {
    public static void main(String[] args) {
        SessionCreator sessionCreator = Winter.getBean(SessionCreator.class);
        Session session = sessionCreator.open();
        try (session) {
            ////////////////////////////////////////////
            session.beginTransaction();
            session.get(User.class,1L);
            session.find(User.class,1L);
            session.load(User.class,1L);
            User sasha = session.find(User.class,12L);
            printState(session);
            session.persist(sasha);
            printState(session);
            session.getTransaction().commit();
            /////////////////////////////////////////////
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }

    private static void printState(Session session) {
        System.out.println("-".repeat(100));
        System.out.printf("Session: %s%n" +
                        "Dirty: %s%n" +
                        "Stat: %s%n",
                session, session.isDirty(), session.getStatistics());
        System.out.println("-".repeat(100));
    }
}
