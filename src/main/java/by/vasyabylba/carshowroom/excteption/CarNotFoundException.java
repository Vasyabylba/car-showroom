package by.vasyabylba.carshowroom.excteption;

import java.util.UUID;

public class CarNotFoundException extends RuntimeException {

    public static final String CAR_NOT_FOUND_BY_ID = "Car with id '%s' not found";

    private CarNotFoundException(String message) {
        super(message);
    }

    public static CarNotFoundException byCarId(UUID carId) {
        return new CarNotFoundException(
                String.format(CAR_NOT_FOUND_BY_ID, carId)
        );
    }

}
