package ca.purpleowl.examples.boot2.feign.service.beta.rest;

import ca.purpleowl.examples.boot2.feign.service.beta.rest.client.ServiceBeta;
import ca.purpleowl.examples.boot2.feign.service.beta.rest.model.Review;
import ca.purpleowl.examples.boot2.feign.service.beta.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController implements ServiceBeta {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public ResponseEntity<List<Review>> getReviewsByBookId(Long bookId) {
        return ResponseEntity.ok(reviewService.getAllByBookId(bookId));
    }

    public ResponseEntity<Review> getReviewById(Long reviewId) {
        return ResponseEntity.of(reviewService.getReviewById(reviewId));
    }

    public ResponseEntity<Review> createReview(Review review) {
        Long id = reviewService.createReview(review);

        if(id != null) {
            review.setId(id);
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
