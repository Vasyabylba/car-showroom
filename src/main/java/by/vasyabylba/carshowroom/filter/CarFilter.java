package by.vasyabylba.carshowroom.filter;

import by.vasyabylba.carshowroom.entity.Car;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Builder
public record CarFilter(
        String model,
        String make,
        Integer releaseYearGte,
        Integer releaseYearLte,
        BigDecimal priceGte,
        BigDecimal priceLte,
        String categoryName,
        String showroomName
) {

    public Specification<Car> toSpecification() {
        return Specification.where(modelSpec())
                .and(makeSpec())
                .and(releaseYearGteSpec())
                .and(releaseYearLteSpec())
                .and(priceGteSpec())
                .and(priceLteSpec())
                .and(categoryNameSpec())
                .and(showroomNameSpec());
    }

    private Specification<Car> modelSpec() {
        return ((root, query, cb) -> StringUtils.hasText(model)
                ? cb.equal(cb.lower(root.get("model")), model.toLowerCase())
                : null);
    }

    private Specification<Car> makeSpec() {
        return ((root, query, cb) -> StringUtils.hasText(make)
                ? cb.equal(cb.lower(root.get("make")), make.toLowerCase())
                : null);
    }

    private Specification<Car> releaseYearGteSpec() {
        return ((root, query, cb) -> releaseYearGte != null
                ? cb.greaterThanOrEqualTo(root.get("releaseYear"), releaseYearGte)
                : null);
    }

    private Specification<Car> releaseYearLteSpec() {
        return ((root, query, cb) -> releaseYearLte != null
                ? cb.lessThanOrEqualTo(root.get("releaseYear"), releaseYearLte)
                : null);
    }

    private Specification<Car> priceGteSpec() {
        return ((root, query, cb) -> priceGte != null
                ? cb.greaterThanOrEqualTo(root.get("price"), priceGte)
                : null);
    }

    private Specification<Car> priceLteSpec() {
        return ((root, query, cb) -> priceLte != null
                ? cb.lessThanOrEqualTo(root.get("price"), priceLte)
                : null);
    }

    private Specification<Car> categoryNameSpec() {
        return ((root, query, cb) -> StringUtils.hasText(categoryName)
                ? cb.equal(cb.lower(root.get("category").get("name")), categoryName.toLowerCase())
                : null);
    }

    private Specification<Car> showroomNameSpec() {
        return ((root, query, cb) -> StringUtils.hasText(showroomName)
                ? cb.equal(cb.lower(root.get("showroom").get("name")), showroomName.toLowerCase())
                : null);
    }

}