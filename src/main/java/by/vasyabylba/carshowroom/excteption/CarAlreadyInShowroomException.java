package by.vasyabylba.carshowroom.excteption;

import java.util.UUID;

public class CarAlreadyInShowroomException extends RuntimeException {

    public static final String CAR_ALREADY_IN_SHOWROOM = "Car with id '%s' already in showroom with id '%s'";

    private CarAlreadyInShowroomException(String message) {
        super(message);
    }

    public static CarAlreadyInShowroomException byCarIdAndShowroomId(UUID carId, UUID showroomId) {
        return new CarAlreadyInShowroomException(
                String.format(CAR_ALREADY_IN_SHOWROOM, carId, showroomId)
        );
    }

}
