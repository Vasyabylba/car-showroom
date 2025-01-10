package by.vasyabylba.carshowroom.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link by.vasyabylba.carshowroom.entity.Review}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    @NotBlank
    private String content;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;

    @NotNull
    private UUID clientId;

    @NotNull
    private UUID carId;

}