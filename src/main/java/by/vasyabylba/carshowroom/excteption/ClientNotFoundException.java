package by.vasyabylba.carshowroom.excteption;

import java.util.UUID;

public class ClientNotFoundException extends RuntimeException {

    public static final String CLIENT_NOT_FOUND_BY_ID = "Client with id '%s' not found";

    private ClientNotFoundException(String message) {
        super(message);
    }

    public static ClientNotFoundException byClientId(UUID clientId) {
        return new ClientNotFoundException(
                String.format(CLIENT_NOT_FOUND_BY_ID, clientId)
        );
    }

}
