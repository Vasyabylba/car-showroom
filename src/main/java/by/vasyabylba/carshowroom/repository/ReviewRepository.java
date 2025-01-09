package by.vasyabylba.carshowroom.repository;

import by.vasyabylba.carshowroom.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {

    List<Review> findByContent(String content);

}