package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.CarRequest;
import by.vasyabylba.carshowroom.dto.CarResponse;
import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import by.vasyabylba.carshowroom.enums.SortDirection;
import by.vasyabylba.carshowroom.excteption.CarAlreadyInShowroomException;
import by.vasyabylba.carshowroom.excteption.CarNotFoundException;
import by.vasyabylba.carshowroom.excteption.CarShowroomNotFoundException;
import by.vasyabylba.carshowroom.excteption.CategoryNotFoundException;
import by.vasyabylba.carshowroom.filter.CarFilter;
import by.vasyabylba.carshowroom.mapper.CarMapper;
import by.vasyabylba.carshowroom.repository.CarRepository;
import by.vasyabylba.carshowroom.repository.CarShowroomRepository;
import by.vasyabylba.carshowroom.repository.CategoryRepository;
import by.vasyabylba.carshowroom.repository.impl.CarRepositoryImpl;
import by.vasyabylba.carshowroom.repository.impl.CarShowroomRepositoryImpl;
import by.vasyabylba.carshowroom.repository.impl.CategoryRepositoryImpl;
import by.vasyabylba.carshowroom.service.CarService;

import java.util.List;
import java.util.UUID;

public class CarServiceImpl implements CarService {

    private static final CarService INSTANCE = new CarServiceImpl();

    private final CarRepository carRepository = CarRepositoryImpl.getInstance();

    private final CategoryRepository categoryRepository = CategoryRepositoryImpl.getInstance();

    private final CarShowroomRepository carShowroomRepository = CarShowroomRepositoryImpl.getInstance();

    public static CarService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<CarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAll(SortDirection sortDirection) {
        List<Car> cars = carRepository.findAll(sortDirection);
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAll(int pageNumber, int pageSize) {
        List<Car> cars = carRepository.findAll(pageNumber, pageSize);
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAll(SortDirection sortDirection, int pageNumber, int pageSize) {
        List<Car> cars = carRepository.findAll(sortDirection, pageNumber, pageSize);
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAll(CarFilter carFilter) {
        List<Car> cars = carRepository.findAll(carFilter);
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAll(CarFilter carFilter, SortDirection sortDirection) {
        List<Car> cars = carRepository.findAll(carFilter, sortDirection);
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAll(CarFilter carFilter, int pageNumber, int pageSize) {
        List<Car> cars = carRepository.findAll(carFilter, pageNumber, pageSize);
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAll(CarFilter carFilter,
                                    SortDirection sortDirection,
                                    int pageNumber,
                                    int pageSize) {
        List<Car> cars = carRepository.findAll(carFilter, sortDirection, pageNumber, pageSize);
        return cars.stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }


    @Override
    public CarResponse getOne(UUID id) {
        Car car = getCarById(id);

        return CarMapper.INSTANCE.toCarResponse(car);
    }

    @Override
    public CarResponse create(CarRequest carRequest) {
        checkAssociations(carRequest);

        Car car = CarMapper.INSTANCE.toEntity(carRequest);

        Car savedCar = carRepository.save(car);

        return CarMapper.INSTANCE.toCarResponse(savedCar);
    }

    @Override
    public CarResponse update(UUID id, CarRequest carRequest) {
        Car car = getCarById(id);
        checkAssociations(carRequest);

        CarMapper.INSTANCE.updateWithNull(carRequest, car);

        Car savedCar = carRepository.update(car);
        return CarMapper.INSTANCE.toCarResponse(savedCar);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            return;
        }

        carRepository.deleteById(id);
    }

    @Override
    public void assignCarToShowroom(UUID carId, UUID showroomId) {
        Car car = getCarById(carId);
        CarShowroom carShowroom = getCarShowroomById(showroomId);

        if (isShowroomContainsCar(carShowroom, car)) {
            throw CarAlreadyInShowroomException.byCarIdAndShowroomId(carId, showroomId);
        }

        car.setShowroom(carShowroom);
        carRepository.update(car);
    }

    @Override
    public List<CarResponse> findByShowroomUsingEntityGraph(UUID showroomId) {
        CarShowroom showroom = getCarShowroomById(showroomId);

        return carRepository.findByShowroomUsingEntityGraph(showroom).stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> findByShowroomUsingJpqlFetch(UUID showroomId) {
        CarShowroom showroom = getCarShowroomById(showroomId);

        return carRepository.findByShowroomUsingJpqlFetch(showroom).stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> findByShowroomUsingCriteriaFetch(UUID showroomId) {
        CarShowroom showroom = getCarShowroomById(showroomId);

        return carRepository.findByShowroomUsingCriteriaFetch(showroom).stream()
                .map(CarMapper.INSTANCE::toCarResponse)
                .toList();
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
