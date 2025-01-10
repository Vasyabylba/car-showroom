package by.vasyabylba.carshowroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link by.vasyabylba.carshowroom.entity.CarShowroom}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarShowroomResponse {

    private UUID id;

    private String name;

    private String address;

}