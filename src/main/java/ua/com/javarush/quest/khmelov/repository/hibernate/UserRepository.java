package ua.com.javarush.quest.khmelov.repository.hibernate;

import org.hibernate.Session;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.Repository;

import java.util.stream.Stream;

public class UserRepository implements Repository<User> {

    private final SessionCreator sessionCreator;

    public UserRepository(SessionCreator sessionCreator) {
        this.sessionCreator = sessionCreator;
    }

    @Override
    public Stream<User> getAll() {
        return null;
    }

    @Override
    public Stream<User> find(User entity) {
        return null;
    }

    @Override
    public User get(long id) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                User user = session.find(User.class, 1L);
                session.getTransaction().commit();
                return user;
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public void create(User user) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                session.save(user);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                session.update(user);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                session.delete(user);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
