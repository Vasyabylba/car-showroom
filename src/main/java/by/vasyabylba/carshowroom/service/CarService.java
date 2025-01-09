package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.CarRequest;
import by.vasyabylba.carshowroom.dto.CarResponse;
import by.vasyabylba.carshowroom.enums.SortDirection;
import by.vasyabylba.carshowroom.filter.CarFilter;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<CarResponse> getAll();

    List<CarResponse> getAll(SortDirection sortDirection);

    List<CarResponse> getAll(int pageNumber, int pageSize);

    List<CarResponse> getAll(SortDirection sortDirection, int pageNumber, int pageSize);

    List<CarResponse> getAll(CarFilter carFilter);

    List<CarResponse> getAll(CarFilter carFilter, SortDirection sortDirection);

    List<CarResponse> getAll(CarFilter carFilter, int pageNumber, int pageSize);

    List<CarResponse> getAll(CarFilter carFilter, SortDirection sortDirection, int pageNumber, int pageSize);

    CarResponse getOne(UUID id);

    CarResponse create(CarRequest carRequest);

    CarResponse update(UUID id, CarRequest carRequest);

    void delete(UUID id);

    void assignCarToShowroom(UUID carId, UUID carShowroomId);

    List<CarResponse> findByShowroomUsingEntityGraph(UUID showroomId);

    List<CarResponse> findByShowroomUsingJpqlFetch(UUID showroomId);

    List<CarResponse> findByShowroomUsingCriteriaFetch(UUID showroomId);

}
