package by.vasyabylba.carshowroom.repository;

import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {

    @EntityGraph(attributePaths = {"category"})
    @Query("SELECT c FROM Car c WHERE c.showroom = :showroom")
    List<Car> findByShowroomUsingEntityGraph(@Param("showroom") CarShowroom showroom);

    @Query("""
            SELECT c 
            FROM Car c 
            LEFT JOIN FETCH c.category 
            WHERE c.showroom = :showroom""")
    List<Car> findByShowroomUsingJpqlFetch(@Param("showroom") CarShowroom showroom);

}