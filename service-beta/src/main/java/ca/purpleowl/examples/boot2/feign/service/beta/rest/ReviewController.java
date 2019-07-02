package ca.purpleowl.examples.boot2.feign.service.beta.rest;

import ca.purpleowl.examples.boot2.feign.service.beta.model.Review;
import ca.purpleowl.examples.boot2.feign.service.beta.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Review>> getReviewsByBookId(@RequestParam("bookId") Long bookId) {
        return ResponseEntity.ok(reviewService.getAllByBookId(bookId));
    }

    @GetMapping(path = "/{reviewId}", produces = "application/json")
    public ResponseEntity<Review> getReviewById(@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.of(reviewService.getReviewById(reviewId));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Long id = reviewService.createReview(review);

        if(id != null) {
            review.setId(id);
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
