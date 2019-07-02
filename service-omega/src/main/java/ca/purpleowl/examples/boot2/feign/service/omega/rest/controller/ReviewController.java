package ca.purpleowl.examples.boot2.feign.service.omega.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.beta.model.Review;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceBetaClient;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ServiceBetaClient serviceBeta;

    @Autowired
    public ReviewController(ServiceBetaClient serviceBeta) {
        this.serviceBeta = serviceBeta;
    }

    @GetMapping(path = "/{reviewId}", produces = "application/json")
    public ResponseEntity<Review> getById(@PathVariable("reviewId") Long reviewId) {
        return serviceBeta.getReviewById(reviewId);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return serviceBeta.createReview(review);
    }
}
