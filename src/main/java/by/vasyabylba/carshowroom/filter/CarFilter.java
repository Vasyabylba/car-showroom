package by.vasyabylba.carshowroom.filter;

import lombok.Builder;

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

}
