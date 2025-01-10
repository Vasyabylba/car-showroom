package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.client.ClientRequest;
import by.vasyabylba.carshowroom.dto.client.ClientResponse;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<ClientResponse> getAll();

    ClientResponse getOne(UUID clientId);

    ClientResponse create(ClientRequest clientRequest);

    ClientResponse update(UUID clientId, ClientRequest clientRequest);

    void delete(UUID clientId);

    void buyCar(UUID clientId, UUID carId);

}
