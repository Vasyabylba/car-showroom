package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.ReviewRequest;
import by.vasyabylba.carshowroom.dto.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<ReviewResponse> getAll();

    ReviewResponse getOne(UUID id);

    ReviewResponse create(ReviewRequest reviewRequest);

    ReviewResponse update(UUID id, ReviewRequest reviewRequest);

    void delete(UUID id);

    List<ReviewResponse> searchReviews(String content);

}
