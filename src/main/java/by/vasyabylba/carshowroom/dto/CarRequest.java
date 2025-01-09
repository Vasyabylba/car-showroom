package by.vasyabylba.carshowroom.dto;

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
public class CarRequest {

    private String model;

    private String make;

    private Integer releaseYear;

    private BigDecimal price;

    private UUID categoryId;

}