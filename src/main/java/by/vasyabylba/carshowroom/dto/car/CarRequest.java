package by.vasyabylba.carshowroom.dto.car;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotBlank
    private String model;

    @NotBlank
    private String make;

    @NotNull
    @Min(1900)
    @Max(3000)
    private Integer releaseYear;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private UUID categoryId;

}