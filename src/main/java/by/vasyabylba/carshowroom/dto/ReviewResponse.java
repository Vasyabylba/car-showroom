package by.vasyabylba.carshowroom.dto;

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
public class ReviewResponse {

    private UUID id;

    private String content;

    private Integer rating;

    private UUID clientId;

    private UUID carId;

}