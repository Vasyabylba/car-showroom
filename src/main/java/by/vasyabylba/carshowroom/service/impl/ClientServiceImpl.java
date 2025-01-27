package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.client.ClientRequest;
import by.vasyabylba.carshowroom.dto.client.ClientResponse;
import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.Client;
import by.vasyabylba.carshowroom.excteption.CarNotFoundException;
import by.vasyabylba.carshowroom.excteption.ClientAlreadyHasCarException;
import by.vasyabylba.carshowroom.excteption.ClientNotFoundException;
import by.vasyabylba.carshowroom.mapper.ClientMapper;
import by.vasyabylba.carshowroom.repository.CarRepository;
import by.vasyabylba.carshowroom.repository.ClientRepository;
import by.vasyabylba.carshowroom.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    private final ClientMapper clientMapper;

    @Override
    public List<ClientResponse> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toClientResponse)
                .toList();
    }

    @Override
    public ClientResponse getOne(UUID clientId) {
        Client client = getClientById(clientId);

        return clientMapper.toClientResponse(client);
    }

    @Override
    public ClientResponse create(ClientRequest clientRequest) {
        Client client = clientMapper.toEntity(clientRequest);

        Client savedClient = clientRepository.save(client);

        return clientMapper.toClientResponse(savedClient);
    }

    @Override
    public ClientResponse update(UUID clientId, ClientRequest clientRequest) {
        Client client = getClientById(clientId);

        clientMapper.updateWithNull(clientRequest, client);

        Client savedClient = clientRepository.save(client);
        return clientMapper.toClientResponse(savedClient);
    }

    @Override
    public void delete(UUID clientId) {
        if (clientId == null) {
            return;
        }

        clientRepository.deleteById(clientId);
    }

    @Override
    public void buyCar(UUID clientId, UUID carId) {
        Client client = getClientWithCarsById(clientId);
        Car car = getCarById(carId);

        if (isClientHasCar(client, car)) {
            throw ClientAlreadyHasCarException.byClientIdAndCarId(clientId, carId);
        }

        client.getCars().add(car);
        clientRepository.save(client);
    }

    private Client getClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> ClientNotFoundException.byClientId(id));
    }

    private Client getClientWithCarsById(UUID id) {
        return clientRepository.findWithCarsById(id)
                .orElseThrow(() -> ClientNotFoundException.byClientId(id));
    }

    private Car getCarById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> CarNotFoundException.byCarId(id));
    }

    private boolean isClientHasCar(Client client, Car car) {
        return client.getCars().stream()
                .anyMatch(clientCar -> clientCar.getId().equals(car.getId()));
    }

}
