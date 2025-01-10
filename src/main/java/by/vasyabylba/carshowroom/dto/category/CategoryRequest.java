package by.vasyabylba.carshowroom.dto.category;

import by.vasyabylba.carshowroom.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link Category}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    @NotBlank
    private String name;

}