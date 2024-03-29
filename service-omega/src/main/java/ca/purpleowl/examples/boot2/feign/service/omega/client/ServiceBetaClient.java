package ca.purpleowl.examples.boot2.feign.service.omega.client;

import ca.purpleowl.examples.boot2.feign.service.beta.model.Review;
import ca.purpleowl.examples.boot2.feign.service.omega.client.fallback.ServiceBetaFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "serviceBeta",
             url = "http://localhost:8710/api",
             fallback = ServiceBetaFallback.class)
public interface ServiceBetaClient {
    @GetMapping(path = "/reviews", produces = "application/json")
    ResponseEntity<List<Review>> getReviewsByBookId(@RequestParam("bookId") Long bookId);

    @GetMapping(path = "/reviews/{reviewId}", produces = "application/json")
    ResponseEntity<Review> getReviewById(@PathVariable("reviewId") Long reviewId);

    @PostMapping(path = "/reviews", produces = "application/json")
    ResponseEntity<Review> createReview(@RequestBody Review review);
}
