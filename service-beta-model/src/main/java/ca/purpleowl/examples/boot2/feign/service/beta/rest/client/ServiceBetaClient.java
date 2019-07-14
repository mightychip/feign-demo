package ca.purpleowl.examples.boot2.feign.service.beta.rest.client;

import ca.purpleowl.examples.boot2.feign.service.beta.rest.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "/reviews")
public interface ServiceBetaClient {
    @GetMapping(produces = "application/json")
    ResponseEntity<List<Review>> getReviewsByBookId(@RequestParam("bookId") Long bookId);

    @GetMapping(path = "/{reviewId}", produces = "application/json")
    ResponseEntity<Review> getReviewById(@PathVariable("reviewId") Long reviewId);

    @PostMapping(produces = "application/json")
    ResponseEntity<Review> createReview(@RequestBody Review review);
}
