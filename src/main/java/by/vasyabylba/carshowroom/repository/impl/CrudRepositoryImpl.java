package by.vasyabylba.carshowroom.repository.impl;

import by.vasyabylba.carshowroom.repository.CrudRepository;
import by.vasyabylba.carshowroom.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CrudRepositoryImpl<T, ID> implements CrudRepository<T, ID> {

    public static final String QUERY_FIND_ALL = "FROM %s";

    private final Class<T> clazz;

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    protected CrudRepositoryImpl(final Class<T> domainClass) {
        this.clazz = domainClass;
    }

    @Override
    public <S extends T> S save(S entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public <S extends T> S update(S entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.merge(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        try (Session session = getSession()) {
            T entity = session.get(clazz, id);
            if (entity == null) return Optional.empty();
            session.detach(entity);
            return Optional.of(entity);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = getSession()) {
            String queryFindAll = QUERY_FIND_ALL.formatted(clazz.getName());
            Query<T> query = session.createQuery(queryFindAll, clazz);
            return query.list();
        }
    }

    @Override
    public void deleteById(ID id) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            T entity = session.get(clazz, id);
            if (entity == null) {
                return;
            }
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

}
