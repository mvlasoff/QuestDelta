package ua.com.javarush.quest.khmelov.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class UserRepository implements Repository<User> {

    private final SessionCreator sessionCreator;

    public UserRepository(SessionCreator sessionCreator) {
        this.sessionCreator = sessionCreator;
    }

    @Override
    public Stream<User> getAll() {
        Session session = sessionCreator.open();
        try (session){
            session.beginTransaction();
            Query<User> queryAll = session.createQuery("select u from User u", User.class);
            List<User> list = queryAll.list();
            session.getTransaction().commit();
            return list.stream();
        } catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Stream<User> find(User entity) {
        Session session = sessionCreator.open();
        try (session){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = query.from(User.class);
            query = query.select(userRoot);
            List<Predicate> predicates=new ArrayList<>();
            Class<User> userClass = User.class;
            Field[] fields = userClass.getDeclaredFields();
            for (Field field : fields) {
                if (
                        !field.isAnnotationPresent(Transient.class) &&
                                !field.isAnnotationPresent(ManyToOne.class) &&
                                !field.isAnnotationPresent(OneToMany.class) &&
                                !field.isAnnotationPresent(OneToOne.class) &&
                                !field.isAnnotationPresent(ManyToMany.class)
                ){
                    field.setAccessible(true);
                    try {
                        String name = field.getName();
                        Object value = field.get(entity);
                        if (Objects.nonNull(value)){
                            Predicate condition = criteriaBuilder.equal(userRoot.get(name), value);
                            predicates.add(condition);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            query=query.where(predicates.toArray(Predicate[]::new));
            List<User> users = session.createQuery(query).list();
            session.getTransaction().commit();
            return users.stream();
        } catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public User get(long id) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                User user = session.get(User.class, id);
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
