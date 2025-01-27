package by.vasyabylba.carshowroom.repository;

import by.vasyabylba.carshowroom.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @EntityGraph(attributePaths = {"cars"})
    @Query("SELECT c FROM Client c WHERE c.id = :clientId")
    Optional<Client> findWithCarsById(@Param("clientId") UUID clientId);

}