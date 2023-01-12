package ua.com.javarush.quest.khmelov.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.com.javarush.quest.khmelov.entity.AbstractEntity;
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

public abstract class HibernateRepository<T extends AbstractEntity> implements Repository<T> {

    private final SessionCreator sessionCreator;
    private final Class<T> aClass;

    public HibernateRepository(SessionCreator sessionCreator, Class<T> aClass) {
        this.sessionCreator = sessionCreator;
        this.aClass = aClass;
    }

    @Override
    public Stream<T> getAll() {
        Session session = sessionCreator.open();
        try (session) {
            session.beginTransaction();
            Query<T> queryAll = session.createQuery("select data from %s data".formatted(aClass.getSimpleName()), aClass);
            List<T> list = queryAll.list();
            session.getTransaction().commit();
            return list.stream();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Stream<T> find(T entity) {
        Session session = sessionCreator.open();
        try (session) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(aClass);
            Root<T> root = query.from(aClass);
            query = query.select(root);
            List<Predicate> predicates = new ArrayList<>();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if (
                        !field.isAnnotationPresent(Transient.class) &&
                                !field.isAnnotationPresent(OneToOne.class) &&
                                !field.isAnnotationPresent(ManyToOne.class) &&
                                !field.isAnnotationPresent(OneToMany.class) &&
                                !field.isAnnotationPresent(ManyToMany.class)
                ) {
                    field.setAccessible(true);
                    try {
                        String name = field.getName();
                        Object value = field.get(entity);
                        if (Objects.nonNull(value)) {
                            Predicate condition = criteriaBuilder.equal(root.get(name), value);
                            predicates.add(condition);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            query = query.where(predicates.toArray(Predicate[]::new));
            List<T> users = session.createQuery(query).list();
            session.getTransaction().commit();
            return users.stream();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public T get(long id) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                T data = session.get(aClass, id);
                session.getTransaction().commit();
                return data;
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public void create(T data) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                session.save(data);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(T data) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                session.update(data);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(T data) {
        try (Session session = sessionCreator.open()) {
            session.beginTransaction();
            try {
                session.delete(data);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
