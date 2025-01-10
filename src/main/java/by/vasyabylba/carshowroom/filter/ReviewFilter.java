package by.vasyabylba.carshowroom.filter;

import by.vasyabylba.carshowroom.entity.Review;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record ReviewFilter(String contentContains, Integer ratingLte, Integer ratingGte) {

    public Specification<Review> toSpecification() {
        return Specification.where(contentContainsSpec())
                .and(ratingLteSpec())
                .and(ratingGteSpec());
    }


    private Specification<Review> contentContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(contentContains)
                ? cb.like(cb.lower(root.get("content")), "%" + contentContains.toLowerCase() + "%")
                : null);
    }

    private Specification<Review> ratingLteSpec() {
        return ((root, query, cb) -> ratingLte != null
                ? cb.lessThanOrEqualTo(root.get("rating"), ratingLte)
                : null);
    }

    private Specification<Review> ratingGteSpec() {
        return ((root, query, cb) -> ratingGte != null
                ? cb.greaterThanOrEqualTo(root.get("rating"), ratingGte)
                : null);
    }

}