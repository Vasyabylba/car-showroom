package by.vasyabylba.carshowroom.repository;

import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import by.vasyabylba.carshowroom.enums.SortDirection;
import by.vasyabylba.carshowroom.filter.CarFilter;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends CrudRepository<Car, UUID> {

    List<Car> findAll(SortDirection sortDirection);

    List<Car> findAll(int pageNumber, int pageSize);

    List<Car> findAll(SortDirection sortDirection, int pageNumber, int pageSize);

    List<Car> findAll(CarFilter carFilter);

    List<Car> findAll(CarFilter carFilter, SortDirection sortDirection);

    List<Car> findAll(CarFilter carFilter, int pageNumber, int pageSize);

    List<Car> findAll(CarFilter carFilter, SortDirection sortDirection, int pageNumber, int pageSize);

    List<Car> findByShowroomUsingEntityGraph(CarShowroom showroom);

    List<Car> findByShowroomUsingJpqlFetch(CarShowroom showroom);

    List<Car> findByShowroomUsingCriteriaFetch(CarShowroom showroom);

}