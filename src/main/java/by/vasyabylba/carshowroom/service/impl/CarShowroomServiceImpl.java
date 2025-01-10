package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.car.CarResponse;
import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomRequest;
import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomResponse;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import by.vasyabylba.carshowroom.excteption.CarShowroomNotFoundException;
import by.vasyabylba.carshowroom.mapper.CarMapper;
import by.vasyabylba.carshowroom.mapper.CarShowroomMapper;
import by.vasyabylba.carshowroom.repository.CarRepository;
import by.vasyabylba.carshowroom.repository.CarShowroomRepository;
import by.vasyabylba.carshowroom.service.CarShowroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarShowroomServiceImpl implements CarShowroomService {

    private final CarShowroomRepository carShowroomRepository;

    private final CarShowroomMapper carShowroomMapper;

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    @Override
    public List<CarShowroomResponse> getAll() {
        List<CarShowroom> carShowrooms = carShowroomRepository.findAll();
        return carShowrooms.stream()
                .map(carShowroomMapper::toCarShowroomResponse)
                .toList();
    }

    @Override
    public CarShowroomResponse getOne(UUID showroomId) {
        CarShowroom carShowroom = getCarShowroomById(showroomId);

        return carShowroomMapper.toCarShowroomResponse(carShowroom);
    }

    @Override
    public CarShowroomResponse create(CarShowroomRequest carShowroomRequest) {
        CarShowroom carShowroom = carShowroomMapper.toEntity(carShowroomRequest);

        CarShowroom savedCarShowroom = carShowroomRepository.save(carShowroom);

        return carShowroomMapper.toCarShowroomResponse(savedCarShowroom);
    }

    @Override
    public CarShowroomResponse update(UUID showroomId, CarShowroomRequest carShowroomRequest) {
        CarShowroom carShowroom = getCarShowroomById(showroomId);

        carShowroomMapper.updateWithNull(carShowroomRequest, carShowroom);

        CarShowroom savedCarShowroom = carShowroomRepository.save(carShowroom);
        return carShowroomMapper.toCarShowroomResponse(savedCarShowroom);
    }

    @Override
    public void delete(UUID showroomId) {
        if (showroomId == null) {
            return;
        }

        carShowroomRepository.deleteById(showroomId);
    }

    @Override
    public List<CarResponse> getCarsByShowroom(UUID showroomId) {
        CarShowroom showroom = getCarShowroomById(showroomId);

        return carRepository.findByShowroomUsingEntityGraph(showroom).stream()
                .map(carMapper::toCarResponse)
                .toList();
    }

    private CarShowroom getCarShowroomById(UUID id) {
        return carShowroomRepository.findById(id)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(id));
    }

}
