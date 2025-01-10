package by.vasyabylba.carshowroom.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link by.vasyabylba.carshowroom.entity.Category}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private UUID id;

    private String name;

}