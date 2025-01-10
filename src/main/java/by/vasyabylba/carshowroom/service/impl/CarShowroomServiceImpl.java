package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.CarShowroomRequest;
import by.vasyabylba.carshowroom.dto.CarShowroomResponse;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import by.vasyabylba.carshowroom.excteption.CarShowroomNotFoundException;
import by.vasyabylba.carshowroom.mapper.CarShowroomMapper;
import by.vasyabylba.carshowroom.repository.CarShowroomRepository;
import by.vasyabylba.carshowroom.repository.impl.CarShowroomRepositoryImpl;
import by.vasyabylba.carshowroom.service.CarShowroomService;

import java.util.List;
import java.util.UUID;


public class CarShowroomServiceImpl implements CarShowroomService {

    private static final CarShowroomService INSTANCE = new CarShowroomServiceImpl();

    private final CarShowroomRepository carShowroomRepository = CarShowroomRepositoryImpl.getInstance();

    public static CarShowroomService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<CarShowroomResponse> getAll() {
        List<CarShowroom> carShowrooms = carShowroomRepository.findAll();
        return carShowrooms.stream()
                .map(CarShowroomMapper.INSTANCE::toCarShowroomResponse)
                .toList();
    }

    @Override
    public CarShowroomResponse getOne(UUID id) {
        CarShowroom carShowroom = getCarShowroomById(id);

        return CarShowroomMapper.INSTANCE.toCarShowroomResponse(carShowroom);
    }

    @Override
    public CarShowroomResponse create(CarShowroomRequest carShowroomRequest) {
        CarShowroom carShowroom = CarShowroomMapper.INSTANCE.toEntity(carShowroomRequest);

        CarShowroom savedCarShowroom = carShowroomRepository.save(carShowroom);

        return CarShowroomMapper.INSTANCE.toCarShowroomResponse(savedCarShowroom);
    }

    @Override
    public CarShowroomResponse update(UUID id, CarShowroomRequest carShowroomRequest) {
        CarShowroom carShowroom = getCarShowroomById(id);

        CarShowroomMapper.INSTANCE.updateWithNull(carShowroomRequest, carShowroom);

        CarShowroom savedCarShowroom = carShowroomRepository.update(carShowroom);
        return CarShowroomMapper.INSTANCE.toCarShowroomResponse(savedCarShowroom);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            return;
        }

        carShowroomRepository.deleteById(id);
    }

    private CarShowroom getCarShowroomById(UUID id) {
        return carShowroomRepository.findById(id)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(id));
    }

}
