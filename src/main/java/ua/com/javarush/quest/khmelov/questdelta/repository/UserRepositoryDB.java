package ua.com.javarush.quest.khmelov.questdelta.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ua.com.javarush.quest.khmelov.questdelta.entity.GameStatistics;
import ua.com.javarush.quest.khmelov.questdelta.entity.Role;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;

import java.util.*;

public class UserRepositoryDB extends MainUserRepository<User> {

    private static final MainUserRepository<User> USER_REPOSITORY_DB = new UserRepositoryDB();

    private final SessionFactory sessionFactory;

    public UserRepositoryDB() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
    }


    public static MainUserRepository<User> get() {
        return USER_REPOSITORY_DB;
    }

    @Override
    public Optional<User> get(long id) {
        User user = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("select u from User u where u.id = :userId", User.class);
            query.setParameter("userId", id);
            query.setMaxResults(1);
            user = query.getSingleResult();
        } catch (Exception e) {
            return Optional.ofNullable(user);
        }
        return Optional.ofNullable(user);
    }

    @SuppressWarnings("CollectionAddAllCanBeReplacedWithConstructor")
    @Override
    public Collection<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("select u from User u", User.class);
            query.setMaxResults(3);
            users.addAll(query.getResultList());
        }
        return users;
    }

    @Override
    public Optional<User> find(String login, String password) {
        User user = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("select u from User u where u.login = :l and u.password = :p", User.class);
            query.setParameter("l", login);
            query.setParameter("p", password);
            query.setMaxResults(1);
            user = query.getSingleResult();
        } catch (Exception e) {
            return Optional.ofNullable(user);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> verify(String login) {
        User user = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("select u from User u where u.login = :l", User.class);
            query.setParameter("l", login);
            query.setMaxResults(1);
            user = query.getSingleResult();
        } catch (Exception e) {
            return Optional.ofNullable(user);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void create(String login, String password, Role role) {
        Session session = sessionFactory.getCurrentSession();
        try (session) {
            session.beginTransaction();
            session.save(new User(login, password, role, new GameStatistics()));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
