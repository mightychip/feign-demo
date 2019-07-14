package ca.purpleowl.examples.boot2.feign.service.omega.service;

import ca.purpleowl.examples.boot2.feign.service.beta.rest.model.Review;
import ca.purpleowl.examples.boot2.feign.service.beta.rest.client.ServiceBetaClient;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class StabilizedServiceBeta extends AbstractStabilizedService<Review> {
    private static final String BULKHEAD_NAME = "serviceAlpha-bulkhead";
    private static final String CIRCUIT_BREAKER_NAME = "serviceAlpha-circuitBreaker";
    private static final String RETRY_NAME = "serviceAlpha-retry";

    private final ServiceBetaClient serviceBetaClient;

    public StabilizedServiceBeta(BulkheadRegistry bulkheadRegistry,
                                 CircuitBreakerRegistry circuitBreakerRegistry,
                                 RetryRegistry retryRegistry,
                                 ServiceBetaClient serviceBetaClient) {
        super(bulkheadRegistry, circuitBreakerRegistry, retryRegistry);
        this.serviceBetaClient = serviceBetaClient;
    }

    public List<Review> getReviewsByBookId(Long bookId) {
        try {
            ResponseEntity<List<Review>> response = wrapAssetListResponse(() -> serviceBetaClient.getReviewsByBookId(bookId));

            if(HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody();
            } else {
                log.warn(String.format("Non-200 response!!  %s", response.getStatusCode().toString()));
            }
        } catch (Throwable throwable) {
            log.error(String.format("Exception occurred calling getReviewsByBookId(%d)", bookId), throwable);
        }

        return Collections.emptyList();
    }

    public Review getReviewById(Long reviewId) {
        try {
            ResponseEntity<Review> response = wrapSingleAssetResopnse(() -> serviceBetaClient.getReviewById(reviewId));

            if(HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody();
            } else {
                log.warn(String.format("Non-200 response!!  %s", response.getStatusCode().toString()));
            }
        } catch (Throwable throwable) {
            log.error(String.format("Exception occurred while calling getReviewById(%d)", reviewId), throwable);
        }

        return new Review();
    }

    public Review createReview(Review review) {
        try {
            ResponseEntity<Review> response = wrapSingleAssetResopnse(() -> serviceBetaClient.createReview(review));

            if(HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody();
            } else {
                log.warn(String.format("Non-200 response!!  %s", response.getStatusCode().toString()));
            }
        } catch (Throwable throwable) {
            log.error(String.format("Exception occurred while calling createReview({%s})", review.toString()), throwable);
        }

        return new Review();
    }

    @Override
    String getBulkheadName() {
        return BULKHEAD_NAME;
    }

    @Override
    String getCircuitBreakerName() {
        return CIRCUIT_BREAKER_NAME;
    }

    @Override
    String getRetryName() {
        return RETRY_NAME;
    }
}
