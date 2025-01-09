package by.vasyabylba.carshowroom.excteption;

import java.util.UUID;

public class ClientAlreadyHasCarException extends RuntimeException {

    public static final String CLIENT_ALREADY_HAS_CAR = "Client with id '%s' already has car with id '%s'";

    private ClientAlreadyHasCarException(String message) {
        super(message);
    }

    public static ClientAlreadyHasCarException byClientIdAndCarId(UUID clientId, UUID carId) {
        return new ClientAlreadyHasCarException(
                String.format(CLIENT_ALREADY_HAS_CAR, clientId, carId)
        );
    }

}
