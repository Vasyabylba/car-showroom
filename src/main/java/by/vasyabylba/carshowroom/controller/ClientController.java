package by.vasyabylba.carshowroom.controller;


import by.vasyabylba.carshowroom.dto.client.ClientRequest;
import by.vasyabylba.carshowroom.dto.client.ClientResponse;
import by.vasyabylba.carshowroom.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse getOne(@PathVariable("id") @Valid @NotBlank UUID id) {
        return clientService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse create(@RequestBody @Valid ClientRequest clientRequest) {
        return clientService.create(clientRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse update(@PathVariable("id") @Valid @NotBlank UUID id,
                                 @RequestBody @Valid ClientRequest clientRequest) {
        return clientService.update(id, clientRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Valid @NotBlank UUID id) {
        clientService.delete(id);
    }

    @PostMapping("/{clientId}/car")
    @ResponseStatus(HttpStatus.OK)
    public void buyCar(@PathVariable("clientId") @Valid @NotBlank UUID clientId,
                       @RequestParam("carId") @Valid @NotBlank UUID carId) {
        clientService.buyCar(clientId, carId);
    }

}

