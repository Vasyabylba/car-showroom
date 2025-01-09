package by.vasyabylba.carshowroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

/**
 * DTO for {@link by.vasyabylba.carshowroom.entity.Client}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    private String name;

    private LocalDate registrationDate;

    private Map<String, String> contacts;

}