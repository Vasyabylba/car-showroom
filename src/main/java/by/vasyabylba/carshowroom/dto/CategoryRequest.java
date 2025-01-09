package by.vasyabylba.carshowroom.dto;

import by.vasyabylba.carshowroom.entity.Category;
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

    private String name;

}