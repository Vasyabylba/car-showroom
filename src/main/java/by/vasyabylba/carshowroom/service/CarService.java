package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.car.CarRequest;
import by.vasyabylba.carshowroom.dto.car.CarResponse;
import by.vasyabylba.carshowroom.filter.CarFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Window;

import java.util.UUID;

public interface CarService {

    Window<CarResponse> getAll(CarFilter carFilter, Pageable pageable);

    CarResponse getOne(UUID carId);

    CarResponse create(CarRequest carRequest);

    CarResponse update(UUID carId, CarRequest carRequest);

    void delete(UUID carId);

    void assignCarToShowroom(UUID carId, UUID carShowroomId);

}
