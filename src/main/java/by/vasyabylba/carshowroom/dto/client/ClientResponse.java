package by.vasyabylba.carshowroom.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * DTO for {@link by.vasyabylba.carshowroom.entity.Client}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private UUID id;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    private Map<String, String> contacts;

}