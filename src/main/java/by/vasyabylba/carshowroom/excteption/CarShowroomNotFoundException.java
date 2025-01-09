package by.vasyabylba.carshowroom.excteption;

import java.util.UUID;

public class CarShowroomNotFoundException extends RuntimeException {

    public static final String CAR_SHOWROOM_NOT_FOUND_BY_ID = "Car showroom with id '%s' not found";

    private CarShowroomNotFoundException(String message) {
        super(message);
    }

    public static CarShowroomNotFoundException byCarShowroomId(UUID showroomId) {
        return new CarShowroomNotFoundException(
                String.format(CAR_SHOWROOM_NOT_FOUND_BY_ID, showroomId)
        );
    }

}
