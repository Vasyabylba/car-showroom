package by.vasyabylba.carshowroom.repository.impl;

import by.vasyabylba.carshowroom.entity.CarShowroom;
import by.vasyabylba.carshowroom.repository.CarShowroomRepository;

import java.util.UUID;

public class CarShowroomRepositoryImpl extends CrudRepositoryImpl<CarShowroom, UUID> implements CarShowroomRepository {

    private static final CarShowroomRepository INSTANCE = new CarShowroomRepositoryImpl();

    private CarShowroomRepositoryImpl() {
        super(CarShowroom.class);
    }

    public static CarShowroomRepository getInstance() {
        return INSTANCE;
    }

}
