package by.vasyabylba.carshowroom.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    @NotNull
    private Map<String, String> contacts;

}