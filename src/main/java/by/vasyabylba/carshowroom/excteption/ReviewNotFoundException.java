package by.vasyabylba.carshowroom.excteption;

import java.util.UUID;

public class ReviewNotFoundException extends RuntimeException {

    public static final String REVIEW_NOT_FOUND_BY_ID = "Review with id '%s' not found";

    private ReviewNotFoundException(String message) {
        super(message);
    }

    public static ReviewNotFoundException byReviewId(UUID reviewId) {
        return new ReviewNotFoundException(
                String.format(REVIEW_NOT_FOUND_BY_ID, reviewId)
        );
    }

}
