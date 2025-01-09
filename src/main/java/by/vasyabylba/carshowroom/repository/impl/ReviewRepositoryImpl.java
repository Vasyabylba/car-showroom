package by.vasyabylba.carshowroom.repository.impl;

import by.vasyabylba.carshowroom.entity.Review;
import by.vasyabylba.carshowroom.repository.ReviewRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;

public class ReviewRepositoryImpl extends CrudRepositoryImpl<Review, UUID> implements ReviewRepository {

    public static final String REVIEW_FIELD_CONTENT = "content";

    public static final String QUERY_LIKE = "%%%s%%";

    private static final ReviewRepository INSTANCE = new ReviewRepositoryImpl();

    private ReviewRepositoryImpl() {
        super(Review.class);
    }

    public static ReviewRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Review> findByContent(String content) {
        try (Session session = getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Review> reviewQuery = cb.createQuery(Review.class);
            Root<Review> reviewRoot = reviewQuery.from(Review.class);

            reviewQuery.select(reviewRoot).where(contentPredicate(cb, reviewRoot, content));

            return session.createQuery(reviewQuery).getResultList();
        }
    }

    private Predicate contentPredicate(CriteriaBuilder cb, Root<Review> root, String content) {
        return cb.like(cb.lower(root.get(REVIEW_FIELD_CONTENT)), String.format(QUERY_LIKE, content.toLowerCase()));
    }

}
