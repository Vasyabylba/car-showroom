package by.vasyabylba.carshowroom.repository;

import by.vasyabylba.carshowroom.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {

    Optional<Client> findByIdWithCars(UUID clientId);

}