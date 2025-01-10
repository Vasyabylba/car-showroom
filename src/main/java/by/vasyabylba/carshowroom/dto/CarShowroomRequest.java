package by.vasyabylba.carshowroom.dto;

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

    private String name;

    private String address;

}