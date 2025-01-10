package by.vasyabylba.carshowroom.repository.impl;

import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import by.vasyabylba.carshowroom.enums.SortDirection;
import by.vasyabylba.carshowroom.filter.CarFilter;
import by.vasyabylba.carshowroom.repository.CarRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarRepositoryImpl extends CrudRepositoryImpl<Car, UUID> implements CarRepository {

    public static final String CAR_FIELD_MODEL = "model";

    public static final String CAR_FIELD_MAKE = "make";

    public static final String CAR_FIELD_RELEASE_YEAR = "releaseYear";

    public static final String CAR_FIELD_PRICE = "price";

    public static final String CAR_FIELD_CATEGORY = "category";

    public static final String CAR_FIELD_SHOWROOM = "showroom";

    public static final String CATEGORY_FIELD_NAME = "name";

    public static final String CAR_SHOWROOM_FIELD_NAME = "name";

    public static final String ENTITY_GRAPH_CAR = "Car.withCategory";

    public static final String QUERY_FIND_CARS_BY_SHOWROOM = """
            SELECT c 
            FROM Car c 
            WHERE c.showroom = :showroom""";

    public static final String HINT_FETCH_GRAPH = "jakarta.persistence.fetchgraph";

    public static final String QUERY_FIND_CARS_BY_SHOWROOM_JOIN_FETCH = """
            SELECT c 
            FROM Car c 
            LEFT JOIN FETCH c.category 
            WHERE c.showroom = :showroom""";

    public static final String SHOWROOM_PARAM_NAME = "showroom";

    public static final String SHOWROOM_FIELD_ID = "id";

    private static final CarRepository INSTANCE = new CarRepositoryImpl();

    private CarRepositoryImpl() {
        super(Car.class);
    }

    public static CarRepository getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Car> findAll(SortDirection sortDirection) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            carCriteriaQuery.select(carRoot);
            addSortDirection(carCriteriaQuery, cb, carRoot, sortDirection);

            return session.createQuery(carCriteriaQuery).getResultList();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> findAll(int pageNumber, int pageSize) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            Query<Car> query = session.createQuery(carCriteriaQuery.select(carRoot));
            addPagination(pageNumber, pageSize, query);

            return query.getResultList();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> findAll(SortDirection sortDirection, int pageNumber, int pageSize) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            addSortDirection(carCriteriaQuery.select(carRoot), cb, carRoot, sortDirection);

            Query<Car> query = session.createQuery(carCriteriaQuery);
            addPagination(pageNumber, pageSize, query);

            return query.getResultList();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> findAll(CarFilter filter) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            carCriteriaQuery.select(carRoot).where(cb.and(getPredicates(filter, cb, carRoot)));

            return session.createQuery(carCriteriaQuery).getResultList();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> findAll(CarFilter filter, SortDirection sortDirection) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            carCriteriaQuery.select(carRoot).where(cb.and(getPredicates(filter, cb, carRoot)));

            addSortDirection(carCriteriaQuery, cb, carRoot, sortDirection);

            return session.createQuery(carCriteriaQuery).getResultList();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> findAll(CarFilter filter, int pageNumber, int pageSize) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            carCriteriaQuery.select(carRoot).where(cb.and(getPredicates(filter, cb, carRoot)));
            Query<Car> query = session.createQuery(carCriteriaQuery);
            addPagination(pageNumber, pageSize, query);

            return query.getResultList();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> findAll(CarFilter filter, SortDirection sortDirection, int pageNumber, int pageSize) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            carCriteriaQuery.select(carRoot).where(cb.and(getPredicates(filter, cb, carRoot)));

            addSortDirection(carCriteriaQuery, cb, carRoot, sortDirection);

            Query<Car> query = session.createQuery(carCriteriaQuery);

            addPagination(pageNumber, pageSize, query);

            return query.getResultList();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> findByShowroomUsingEntityGraph(CarShowroom showroom) {
        try (
                Session session = getSession();
                EntityManager entityManager = session.getEntityManagerFactory().createEntityManager()
        ) {
            EntityGraph<?> entityGraph = entityManager.getEntityGraph(ENTITY_GRAPH_CAR);

            return session.createQuery(QUERY_FIND_CARS_BY_SHOWROOM, Car.class)
                    .setParameter(SHOWROOM_PARAM_NAME, showroom)
                    .setHint(HINT_FETCH_GRAPH, entityGraph)
                    .getResultList();
        }
    }

    @Override
    public List<Car> findByShowroomUsingJpqlFetch(CarShowroom showroom) {
        try (Session session = getSession()) {
            return session.createQuery(QUERY_FIND_CARS_BY_SHOWROOM_JOIN_FETCH, Car.class)
                    .setParameter(SHOWROOM_PARAM_NAME, showroom)
                    .getResultList();
        }
    }

    @Override
    public List<Car> findByShowroomUsingCriteriaFetch(CarShowroom showroom) {
        try (Session session = getSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> carQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = carQuery.from(Car.class);
            carRoot.fetch(CAR_FIELD_CATEGORY, JoinType.LEFT);
            carQuery.select(carRoot)
                    .where(cb.equal(carRoot.get(CAR_FIELD_SHOWROOM).get(SHOWROOM_FIELD_ID), showroom.getId()));

            return session.createQuery(carQuery).getResultList();
        }
    }

    private Predicate[] getPredicates(CarFilter filter, CriteriaBuilder cb, Root<Car> carRoot) {
        List<Predicate> predicates = new ArrayList<>();
        addModelPredicate(filter.model(), predicates, cb, carRoot);
        addMakePredicate(filter.make(), predicates, cb, carRoot);
        addCategoryPredicate(filter.categoryName(), predicates, cb, carRoot);
        addShowroomPredicate(filter.showroomName(), predicates, cb, carRoot);
        addReleaseYearPredicates(filter, predicates, cb, carRoot);
        addPricePredicates(filter, predicates, cb, carRoot);
        return predicates.toArray(new Predicate[0]);
    }

    private void addModelPredicate(String model, List<Predicate> predicates, CriteriaBuilder cb, Root<Car> root) {
        if (model != null && !model.isEmpty()) {
            predicates.add(cb.equal(cb.lower(root.get(CAR_FIELD_MODEL)), model.toLowerCase()));
        }
    }

    private void addMakePredicate(String make, List<Predicate> predicates, CriteriaBuilder cb, Root<Car> carRoot) {
        if (make != null && !make.isEmpty()) {
            predicates.add(cb.equal(cb.lower(carRoot.get(CAR_FIELD_MAKE)), make.toLowerCase()));
        }
    }

    private void addCategoryPredicate(String categoryName,
                                      List<Predicate> predicates,
                                      CriteriaBuilder cb,
                                      Root<Car> carRoot) {
        if (categoryName != null && !categoryName.isEmpty()) {
            predicates.add(cb.equal(
                    cb.lower(carRoot.get(CAR_FIELD_CATEGORY).get(CATEGORY_FIELD_NAME)), categoryName.toLowerCase()
            ));
        }
    }

    private void addReleaseYearPredicates(CarFilter filter,
                                          List<Predicate> predicates,
                                          CriteriaBuilder cb,
                                          Root<Car> carRoot) {
        Integer releaseYearLte = filter.releaseYearLte();
        if (releaseYearLte != null) {
            predicates.add(cb.lessThanOrEqualTo(carRoot.get(CAR_FIELD_RELEASE_YEAR), releaseYearLte));
        }

        Integer releaseYearGte = filter.releaseYearGte();
        if (releaseYearGte != null) {
            predicates.add(cb.greaterThanOrEqualTo(carRoot.get(CAR_FIELD_RELEASE_YEAR), releaseYearGte));
        }
    }

    private void addPricePredicates(CarFilter filter,
                                    List<Predicate> predicates,
                                    CriteriaBuilder cb,
                                    Root<Car> carRoot) {
        BigDecimal priceLte = filter.priceLte();
        if (priceLte != null) {
            predicates.add(cb.lessThanOrEqualTo(carRoot.get(CAR_FIELD_PRICE), priceLte));
        }

        BigDecimal priceGte = filter.priceGte();
        if (priceGte != null) {
            predicates.add(cb.greaterThanOrEqualTo(carRoot.get(CAR_FIELD_PRICE), priceGte));
        }
    }

    private void addShowroomPredicate(String showroomName,
                                      List<Predicate> predicates,
                                      CriteriaBuilder cb,
                                      Root<Car> carRoot) {
        if (showroomName != null && !showroomName.isEmpty()) {
            predicates.add(cb.equal(
                    cb.lower(carRoot.get(CAR_FIELD_SHOWROOM).get(CAR_SHOWROOM_FIELD_NAME)),
                    showroomName.toLowerCase()
            ));
        }
    }

    private void addSortDirection(CriteriaQuery<Car> carQuery,
                                  CriteriaBuilder cb,
                                  Root<Car> carRoot,
                                  SortDirection direction) {
        if (direction.isDescending()) {
            carQuery.orderBy(cb.desc(carRoot.get(CAR_FIELD_PRICE)));
            return;
        }
        carQuery.orderBy(cb.asc(carRoot.get(CAR_FIELD_PRICE)));
    }

    private void addPagination(int pageNumber, int pageSize, Query<Car> query) {
        int offset = (pageNumber - 1) * pageSize;
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
    }

}
