package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.CarShowroomRequest;
import by.vasyabylba.carshowroom.dto.CarShowroomResponse;

import java.util.List;
import java.util.UUID;

public interface CarShowroomService {

    List<CarShowroomResponse> getAll();

    CarShowroomResponse getOne(UUID id);

    CarShowroomResponse create(CarShowroomRequest carShowroomRequest);

    CarShowroomResponse update(UUID id, CarShowroomRequest carShowroomRequest);

    void delete(UUID id);

}
