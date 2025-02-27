package by.vasyabylba.carshowroom.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link by.vasyabylba.carshowroom.entity.Car}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private UUID id;

    private String model;

    private String make;

    private Integer releaseYear;

    private BigDecimal price;

    private UUID categoryId;

}