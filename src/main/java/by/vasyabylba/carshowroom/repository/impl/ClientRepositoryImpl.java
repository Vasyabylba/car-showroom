package by.vasyabylba.carshowroom.repository.impl;

import by.vasyabylba.carshowroom.entity.Client;
import by.vasyabylba.carshowroom.repository.ClientRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryImpl extends CrudRepositoryImpl<Client, UUID> implements ClientRepository {

    public static final String QUERY_FIND_CLIENT_BY_ID = """
            SELECT c 
            FROM Client c 
            LEFT JOIN FETCH c.cars 
            WHERE c.id = :clientId""";

    public static final String CLIENT_ID_PARAM = "clientId";

    private static final ClientRepository INSTANCE = new ClientRepositoryImpl();

    private ClientRepositoryImpl() {
        super(Client.class);
    }

    public static ClientRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Client> findByIdWithCars(UUID clientId) {
        try (Session session = getSession()) {
            Query<Client> clientQuery = session.createQuery(QUERY_FIND_CLIENT_BY_ID, Client.class);
            clientQuery.setParameter(CLIENT_ID_PARAM, clientId);

            return Optional.ofNullable(clientQuery.getSingleResultOrNull());
        }
    }

}
