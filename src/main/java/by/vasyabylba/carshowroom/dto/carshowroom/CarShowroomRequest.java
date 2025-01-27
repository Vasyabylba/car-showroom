package by.vasyabylba.carshowroom.dto.carshowroom;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link by.vasyabylba.carshowroom.entity.CarShowroom}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarShowroomRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

}