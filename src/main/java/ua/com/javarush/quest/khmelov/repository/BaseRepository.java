package ua.com.javarush.quest.khmelov.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.com.javarush.quest.khmelov.entity.AbstractEntity;

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

public abstract class BaseRepository<T extends AbstractEntity> implements Repository<T> {

    private final SessionCreator sessionCreator;
    private final Class<T> aClass;

    public BaseRepository(SessionCreator sessionCreator, Class<T> aClass) {
        this.sessionCreator = sessionCreator;
        this.aClass = aClass;
    }

    @Override
    public Stream<T> getAll() {
        beginTransactional();
        try {
            Session session = sessionCreator.get();
            Query<T> queryAll = session.createQuery("select data from %s data".formatted(aClass.getSimpleName()), aClass);
            List<T> list = queryAll.list();
            return list.stream();
        } finally {
            endTransactional();
        }
    }

    @Override
    public Stream<T> find(T entity) {
        beginTransactional();
        try {
            Session session = sessionCreator.get();
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
            return users.stream();
        } finally {
            endTransactional();
        }
    }

    @Override
    public T get(long id) {
        beginTransactional();
        try {
            Session session = sessionCreator.get();
            return session.get(aClass, id);
        } finally {
            endTransactional();
        }
    }

    @Override
    public void create(T data) {
        beginTransactional();
        try {
            Session session = sessionCreator.get();
            session.save(data);
        } finally {
            endTransactional();
        }
    }

    @Override
    public void update(T data) {
        beginTransactional();
        try {
            Session session = sessionCreator.get();
            session.update(data);
        } finally {
            endTransactional();
        }
    }

    @Override
    public void delete(T data) {
        beginTransactional();
        try {
            Session session = sessionCreator.get();
            session.delete(data);
        } finally {
            endTransactional();
        }
    }

    public Transaction beginTransactional() {
        return sessionCreator.startTransactional();
    }

    public void endTransactional() {
        sessionCreator.endTransactional();
    }


}
