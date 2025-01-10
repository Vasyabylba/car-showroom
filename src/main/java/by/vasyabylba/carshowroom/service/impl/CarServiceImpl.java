package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.car.CarRequest;
import by.vasyabylba.carshowroom.dto.car.CarResponse;
import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import by.vasyabylba.carshowroom.excteption.CarAlreadyInShowroomException;
import by.vasyabylba.carshowroom.excteption.CarNotFoundException;
import by.vasyabylba.carshowroom.excteption.CarShowroomNotFoundException;
import by.vasyabylba.carshowroom.excteption.CategoryNotFoundException;
import by.vasyabylba.carshowroom.filter.CarFilter;
import by.vasyabylba.carshowroom.logging.Logging;
import by.vasyabylba.carshowroom.mapper.CarMapper;
import by.vasyabylba.carshowroom.repository.CarRepository;
import by.vasyabylba.carshowroom.repository.CarShowroomRepository;
import by.vasyabylba.carshowroom.repository.CategoryRepository;
import by.vasyabylba.carshowroom.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Logging
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CategoryRepository categoryRepository;

    private final CarShowroomRepository carShowroomRepository;

    private final CarMapper carMapper;

    @Override
    public Window<CarResponse> getAll(CarFilter carFilter, Pageable pageable) {
        Specification<Car> carSpecification = carFilter.toSpecification();
        Window<Car> cars = carRepository.findBy(
                carSpecification, fluentQuery -> fluentQuery
                        .sortBy(pageable.getSort())
                        .limit(pageable.getPageSize())
                        .scroll(pageable.toScrollPosition())
        );

        return cars.map(carMapper::toCarResponse);
    }

    @Override
    public CarResponse getOne(UUID carId) {
        Car car = getCarById(carId);

        return carMapper.toCarResponse(car);
    }

    @Override
    public CarResponse create(CarRequest carRequest) {
        checkAssociations(carRequest);

        Car car = carMapper.toEntity(carRequest);

        Car savedCar = carRepository.save(car);

        return carMapper.toCarResponse(savedCar);
    }

    @Override
    public CarResponse update(UUID carId, CarRequest carRequest) {
        Car car = getCarById(carId);
        checkAssociations(carRequest);

        carMapper.updateWithNull(carRequest, car);

        Car savedCar = carRepository.save(car);
        return carMapper.toCarResponse(savedCar);
    }

    @Override
    public void delete(UUID carId) {
        if (carId == null) {
            return;
        }

        carRepository.deleteById(carId);
    }

    @Override
    public void assignCarToShowroom(UUID carId, UUID showroomId) {
        Car car = getCarById(carId);
        CarShowroom carShowroom = getCarShowroomById(showroomId);

        if (isShowroomContainsCar(carShowroom, car)) {
            throw CarAlreadyInShowroomException.byCarIdAndShowroomId(carId, showroomId);
        }

        car.setShowroom(carShowroom);
        carRepository.save(car);
    }

    private void checkAssociations(CarRequest carRequest) {
        checkIfCategoryExists(carRequest.getCategoryId());
    }

    private void checkIfCategoryExists(UUID categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.byCategoryId(categoryId));
    }

    private Car getCarById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> CarNotFoundException.byCarId(id));
    }

    private CarShowroom getCarShowroomById(UUID id) {
        return carShowroomRepository.findById(id)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(id));
    }

    private boolean isShowroomContainsCar(CarShowroom carShowroom, Car car) {
        return getShowroomCars(carShowroom).stream()
                .anyMatch(showroomCar -> showroomCar.getId().equals(car.getId()));
    }

    private List<Car> getShowroomCars(CarShowroom showroom) {
        return carRepository.findByShowroomUsingEntityGraph(showroom);
    }

}
