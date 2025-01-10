package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.review.ReviewRequest;
import by.vasyabylba.carshowroom.dto.review.ReviewResponse;
import by.vasyabylba.carshowroom.entity.Review;
import by.vasyabylba.carshowroom.excteption.CarNotFoundException;
import by.vasyabylba.carshowroom.excteption.ClientNotFoundException;
import by.vasyabylba.carshowroom.excteption.ReviewNotFoundException;
import by.vasyabylba.carshowroom.filter.ReviewFilter;
import by.vasyabylba.carshowroom.mapper.ReviewMapper;
import by.vasyabylba.carshowroom.repository.CarRepository;
import by.vasyabylba.carshowroom.repository.ClientRepository;
import by.vasyabylba.carshowroom.repository.ReviewRepository;
import by.vasyabylba.carshowroom.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewResponse> getAll(ReviewFilter reviewFilter) {
        Specification<Review> reviewSpecification = reviewFilter.toSpecification();
        List<Review> reviews = reviewRepository.findAll(reviewSpecification);
        return reviews.stream()
                .map(reviewMapper::toReviewResponse)
                .toList();
    }

    @Override
    public ReviewResponse getOne(UUID reviewId) {
        Review review = getReviewById(reviewId);

        return reviewMapper.toReviewResponse(review);
    }

    @Override
    public ReviewResponse create(ReviewRequest reviewRequest) {
        checkAssociations(reviewRequest);

        Review review = reviewMapper.toEntity(reviewRequest);

        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toReviewResponse(savedReview);
    }

    @Override
    public ReviewResponse update(UUID reviewId, ReviewRequest reviewRequest) {
        Review review = getReviewById(reviewId);
        checkAssociations(reviewRequest);

        reviewMapper.updateWithNull(reviewRequest, review);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewResponse(savedReview);
    }

    @Override
    public void delete(UUID reviewId) {
        if (reviewId == null) {
            return;
        }

        reviewRepository.deleteById(reviewId);
    }

    private void checkAssociations(ReviewRequest reviewRequest) {
        checkIfClientExists(reviewRequest.getClientId());
        checkIfCarExists(reviewRequest.getCarId());
    }

    private void checkIfClientExists(UUID clientId) {
        clientRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.byClientId(clientId));
    }

    private void checkIfCarExists(UUID carId) {
        carRepository.findById(carId)
                .orElseThrow(() -> CarNotFoundException.byCarId(carId));
    }

    private Review getReviewById(UUID id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> ReviewNotFoundException.byReviewId(id));
    }

}
