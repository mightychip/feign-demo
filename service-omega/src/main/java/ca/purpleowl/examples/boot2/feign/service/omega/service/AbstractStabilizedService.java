package ca.purpleowl.examples.boot2.feign.service.omega.service;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
public abstract class AbstractStabilizedService<T> {
    private final CircuitBreaker circuitBreaker;
    private final Bulkhead bulkhead;
    private final Retry retry;

    public AbstractStabilizedService(BulkheadRegistry bulkheadRegistry,
                                     CircuitBreakerRegistry circuitBreakerRegistry,
                                     RetryRegistry retryRegistry) {
        this.bulkhead = Bulkhead.of(getBulkheadName(), bulkheadRegistry.getDefaultConfig());
        this.circuitBreaker = CircuitBreaker.of(getCircuitBreakerName(), circuitBreakerRegistry.getConfiguration("defaultConfig").get());
        this.retry = Retry.of(getRetryName(), retryRegistry.getDefaultConfig());
    }

    abstract String getBulkheadName();
    abstract String getCircuitBreakerName();
    abstract String getRetryName();

    ResponseEntity<T> wrapSingleAssetResopnse(Supplier<ResponseEntity<T>> apiCall) throws Throwable {
        Supplier<ResponseEntity<T>> decoratedSupplier =
                Decorators.ofSupplier(apiCall)
                          .withBulkhead(bulkhead)
                          .withCircuitBreaker(circuitBreaker)
                          .withRetry(retry)
                          .decorate();

        Try<ResponseEntity<T>> result = Try.ofSupplier(decoratedSupplier);

        if(result.isSuccess()) {
            return result.get();
        } else {
            throw handleFailure(result);
        }
    }

    ResponseEntity<List<T>> wrapAssetListResponse(Supplier<ResponseEntity<List<T>>> apiCall) throws Throwable {
        Supplier<ResponseEntity<List<T>>> decoratedSupplier =
                CircuitBreaker.decorateSupplier(circuitBreaker, apiCall);

        Try<ResponseEntity<List<T>>> result = Try.ofSupplier(decoratedSupplier);

        if(result.isSuccess()) {
            return result.get();
        } else {
            throw handleFailure(result);
        }
    }

    /**
     * This method simply takes a request that we know has failed and - if the failure is due to a Circuit Breaker being
     * open - logs appropriately.
     *
     * @param failedRequest - A failed {@link Try}.
     * @return The {@link Throwable} emitted by the failure.
     */
    private Throwable handleFailure(Try failedRequest) {
        if(failedRequest.getCause() instanceof CallNotPermittedException) {
            log.warn("Circuit Breaker Open!!");
        }
        return failedRequest.getCause();
    }
}
