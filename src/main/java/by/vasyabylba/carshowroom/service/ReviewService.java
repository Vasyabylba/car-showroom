package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.review.ReviewRequest;
import by.vasyabylba.carshowroom.dto.review.ReviewResponse;
import by.vasyabylba.carshowroom.filter.ReviewFilter;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<ReviewResponse> getAll(ReviewFilter reviewFilter);

    ReviewResponse getOne(UUID reviewId);

    ReviewResponse create(ReviewRequest reviewRequest);

    ReviewResponse update(UUID reviewId, ReviewRequest reviewRequest);

    void delete(UUID reviewId);

}
