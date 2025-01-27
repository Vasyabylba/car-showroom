package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.car.CarResponse;
import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomRequest;
import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomResponse;

import java.util.List;
import java.util.UUID;

public interface CarShowroomService {

    List<CarShowroomResponse> getAll();

    CarShowroomResponse getOne(UUID showroomId);

    CarShowroomResponse create(CarShowroomRequest carShowroomRequest);

    CarShowroomResponse update(UUID showroomId, CarShowroomRequest carShowroomRequest);

    void delete(UUID showroomId);

    List<CarResponse> getCarsByShowroom(UUID showroomId);

}
