package by.vasyabylba.carshowroom.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> extends Repository<T, ID> {

    <S extends T> S save(S entity);

    <S extends T> S update(S entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

}
