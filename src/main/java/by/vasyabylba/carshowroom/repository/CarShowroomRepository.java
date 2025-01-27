package by.vasyabylba.carshowroom.repository;

import by.vasyabylba.carshowroom.entity.CarShowroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, UUID> {

}