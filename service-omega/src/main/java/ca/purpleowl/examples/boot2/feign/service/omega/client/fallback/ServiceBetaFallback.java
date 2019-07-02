package ca.purpleowl.examples.boot2.feign.service.omega.client.fallback;

import ca.purpleowl.examples.boot2.feign.service.beta.model.Review;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceBetaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class ServiceBetaFallback implements ServiceBetaClient {
    @Override
    public ResponseEntity<List<Review>> getReviewsByBookId(Long bookId) {
        log.warn("Executing fallback: getReviewsByBookId");
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Review> getReviewById(Long reviewId) {
        log.warn("Executing fallback: getReviewById");
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(new Review());
    }

    @Override
    public ResponseEntity<Review> createReview(Review review) {
        log.warn("Executing fallback: createReview");
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(review);
    }
}
