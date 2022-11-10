package ua.com.javarush.quest.khmelov.repository.hibernate;

import org.hibernate.Session;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.User;
public class HiberRunner {
    public static void main(String[] args) {

        SessionCreator sessionCreator = Winter.getBean(SessionCreator.class);
        Session session = sessionCreator.open();
        session.beginTransaction();
        User user = session.find(User.class, 1L);
        System.out.println(user);
        session.getTransaction().commit();
    }
}
