package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.ClientRequest;
import by.vasyabylba.carshowroom.dto.ClientResponse;
import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.Client;
import by.vasyabylba.carshowroom.excteption.CarNotFoundException;
import by.vasyabylba.carshowroom.excteption.ClientAlreadyHasCarException;
import by.vasyabylba.carshowroom.excteption.ClientNotFoundException;
import by.vasyabylba.carshowroom.mapper.ClientMapper;
import by.vasyabylba.carshowroom.repository.CarRepository;
import by.vasyabylba.carshowroom.repository.ClientRepository;
import by.vasyabylba.carshowroom.repository.impl.CarRepositoryImpl;
import by.vasyabylba.carshowroom.repository.impl.ClientRepositoryImpl;
import by.vasyabylba.carshowroom.service.ClientService;

import java.util.List;
import java.util.UUID;


public class ClientServiceImpl implements ClientService {

    private static final ClientService INSTANCE = new ClientServiceImpl();

    private final ClientRepository clientRepository = ClientRepositoryImpl.getInstance();

    private final CarRepository carRepository = CarRepositoryImpl.getInstance();

    public static ClientService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<ClientResponse> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientMapper.INSTANCE::toClientResponse)
                .toList();
    }

    @Override
    public ClientResponse getOne(UUID id) {
        Client client = getClientById(id);

        return ClientMapper.INSTANCE.toClientResponse(client);
    }

    @Override
    public ClientResponse create(ClientRequest clientRequest) {
        Client client = ClientMapper.INSTANCE.toEntity(clientRequest);

        Client savedClient = clientRepository.save(client);

        return ClientMapper.INSTANCE.toClientResponse(savedClient);
    }

    @Override
    public ClientResponse update(UUID id, ClientRequest clientRequest) {
        Client client = getClientById(id);

        ClientMapper.INSTANCE.updateWithNull(clientRequest, client);

        Client savedClient = clientRepository.update(client);
        return ClientMapper.INSTANCE.toClientResponse(savedClient);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            return;
        }

        clientRepository.deleteById(id);
    }

    @Override
    public void buyCar(UUID clientId, UUID carId) {
        Client client = getClientByIdWithCars(clientId);
        Car car = getCarById(carId);

        if (isClientHasCar(client, car)) {
            throw ClientAlreadyHasCarException.byClientIdAndCarId(clientId, carId);
        }

        client.getCars().add(car);
        clientRepository.update(client);
    }

    private Client getClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> ClientNotFoundException.byClientId(id));
    }

    private Client getClientByIdWithCars(UUID id) {
        return clientRepository.findByIdWithCars(id)
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
