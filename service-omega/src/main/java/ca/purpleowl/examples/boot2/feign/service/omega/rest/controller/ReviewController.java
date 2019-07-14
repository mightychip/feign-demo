package ca.purpleowl.examples.boot2.feign.service.omega.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.beta.rest.model.Review;
import ca.purpleowl.examples.boot2.feign.service.omega.service.StabilizedServiceBeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {
    private final StabilizedServiceBeta serviceBeta;

    @Autowired
    public ReviewController(StabilizedServiceBeta serviceBeta) {
        this.serviceBeta = serviceBeta;
    }

    @GetMapping(path = "/{reviewId}", produces = "application/json")
    public ResponseEntity<Review> getById(@PathVariable("reviewId") Long reviewId) {
        Review response = serviceBeta.getReviewById(reviewId);
        return response.getId() != null ?
                ResponseEntity.ok(response) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review response = serviceBeta.createReview(review);
        return response.getId() != null ?
                ResponseEntity.ok(response) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
