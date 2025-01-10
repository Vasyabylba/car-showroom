package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.ClientRequest;
import by.vasyabylba.carshowroom.dto.ClientResponse;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<ClientResponse> getAll();

    ClientResponse getOne(UUID id);

    ClientResponse create(ClientRequest clientRequest);

    ClientResponse update(UUID id, ClientRequest clientRequest);

    void delete(UUID id);

    void buyCar(UUID clientId, UUID carId);

}
