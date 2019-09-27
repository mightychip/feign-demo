package ca.purpleowl.examples.boot2.feign.service.omega.service;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.client.ServiceAlpha;
import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * This is an implementation of Resilience4J in top of a Feign client to provide various flavours of fault tolerance,
 * including Bulkheads, Circuit Breakers, and Retries.
 */
@Service
@Slf4j
public class StabilizedServiceAlpha extends AbstractStabilizedService<Book> {
    private static final String BULKHEAD_NAME = "serviceAlpha-bulkhead";
    private static final String CIRCUIT_BREAKER_NAME = "serviceAlpha-circuitBreaker";
    private static final String RETRY_NAME = "serviceAlpha-retry";

    private final ServiceAlpha serviceAlpha;

    public StabilizedServiceAlpha(BulkheadRegistry bulkheadRegistry,
                                  CircuitBreakerRegistry circuitBreakerRegistry,
                                  RetryRegistry retryRegistry,
                                  ServiceAlpha serviceAlpha) {
        super(bulkheadRegistry, circuitBreakerRegistry, retryRegistry);
        this.serviceAlpha = serviceAlpha;
    }


    public List<Book> getBooksByAuthor(String authorName) {
        try {
            ResponseEntity<List<Book>> response = wrapAssetListResponse(() -> serviceAlpha.getBooksByAuthor(authorName));

            if(HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody();
            } else {
                log.warn(String.format("Non-200 response!!  %s", response.getStatusCode().toString()));
            }
        } catch (Throwable throwable) {
            log.error(String.format("Exception occurred while calling getBooksByAuthor(%s)", authorName), throwable);
        }

        return Collections.emptyList();
    }

    public Book getBookById(Long bookId) {
        try {
            ResponseEntity<Book> response = wrapSingleAssetResopnse(() -> serviceAlpha.getBookById(bookId));

            if(HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody();
            } else {
                log.warn(String.format("Non-200 response!!  %s", response.getStatusCode().toString()));
            }
        } catch (Throwable throwable) {
            log.error(String.format("Exception occurred while calling getBookById(%d)", bookId), throwable);
        }

        return new Book();
    }

    public Book createBook(Book book) {
        try {
            ResponseEntity<Book> response = wrapSingleAssetResopnse(() -> serviceAlpha.createBook(book));

            if(HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody();
            } else {
                log.warn(String.format("Non-200 response!!  %s", response.getStatusCode().toString()));
            }
        } catch (Throwable throwable) {
            log.error(String.format("Exception occurred while calling createBook({%s})", book.toString()), throwable);
        }

        return new Book();
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
