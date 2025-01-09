package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.ReviewRequest;
import by.vasyabylba.carshowroom.dto.ReviewResponse;
import by.vasyabylba.carshowroom.entity.Review;
import by.vasyabylba.carshowroom.excteption.CarNotFoundException;
import by.vasyabylba.carshowroom.excteption.ClientNotFoundException;
import by.vasyabylba.carshowroom.excteption.ReviewNotFoundException;
import by.vasyabylba.carshowroom.mapper.ReviewMapper;
import by.vasyabylba.carshowroom.repository.CarRepository;
import by.vasyabylba.carshowroom.repository.ClientRepository;
import by.vasyabylba.carshowroom.repository.ReviewRepository;
import by.vasyabylba.carshowroom.repository.impl.CarRepositoryImpl;
import by.vasyabylba.carshowroom.repository.impl.ClientRepositoryImpl;
import by.vasyabylba.carshowroom.repository.impl.ReviewRepositoryImpl;
import by.vasyabylba.carshowroom.service.ReviewService;

import java.util.List;
import java.util.UUID;


public class ReviewServiceImpl implements ReviewService {

    private static final ReviewService INSTANCE = new ReviewServiceImpl();

    private final CarRepository carRepository = CarRepositoryImpl.getInstance();

    private final ClientRepository clientRepository = ClientRepositoryImpl.getInstance();


    private final ReviewRepository reviewRepository = ReviewRepositoryImpl.getInstance();

    public static ReviewService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(ReviewMapper.INSTANCE::toReviewResponse)
                .toList();
    }

    @Override
    public ReviewResponse getOne(UUID id) {
        Review review = getReviewById(id);

        return ReviewMapper.INSTANCE.toReviewResponse(review);
    }

    @Override
    public ReviewResponse create(ReviewRequest reviewRequest) {
        checkAssociations(reviewRequest);

        Review review = ReviewMapper.INSTANCE.toEntity(reviewRequest);

        Review savedReview = reviewRepository.save(review);

        return ReviewMapper.INSTANCE.toReviewResponse(savedReview);
    }

    @Override
    public ReviewResponse update(UUID id, ReviewRequest reviewRequest) {
        Review review = getReviewById(id);
        checkAssociations(reviewRequest);

        ReviewMapper.INSTANCE.updateWithNull(reviewRequest, review);

        Review savedReview = reviewRepository.update(review);
        return ReviewMapper.INSTANCE.toReviewResponse(savedReview);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            return;
        }

        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewResponse> searchReviews(String content) {
        List<Review> reviews = reviewRepository.findByContent(content);
        return reviews.stream()
                .map(ReviewMapper.INSTANCE::toReviewResponse)
                .toList();
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
