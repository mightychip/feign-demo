package ca.purpleowl.examples.boot2.feign.service.omega.service;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceAlphaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AnnotatedServiceAlpha {
    private static final String CIRCUIT_BREAKER_NAME = "serviceAlpha";

    private final ServiceAlphaClient serviceAlpha;

    public AnnotatedServiceAlpha(ServiceAlphaClient serviceAlpha) {
        this.serviceAlpha = serviceAlpha;
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getBooksByAuthorFallback")
    public ResponseEntity<List<Book>> getBooksByAuthor(String authorName) {
        return serviceAlpha.getBooksByAuthor(authorName);
    }

    public ResponseEntity<List<Book>> getBooksByAuthorFallback(String authorName, Throwable exception) {
        log.warn(String.format("While calling getBooksByAuthor(%s), experienced error: %s", authorName, exception.getMessage()), exception);
        return ResponseEntity.ok(Collections.emptyList());
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getBookByIdFallback")
    public ResponseEntity<Book> getBookById(Long bookId) {
        return serviceAlpha.getBookById(bookId);
    }

    public ResponseEntity<Book> getBookByIdFallback(Long bookId, Throwable exception) {
        log.warn(String.format("While calling getBookById(%d), experienced error: %s", bookId, exception.getMessage()), exception);
        return ResponseEntity.ok(new Book());
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "createBookFallback")
    public ResponseEntity<Book> createBook(Book book) {
        return serviceAlpha.createBook(book);
    }

    public ResponseEntity<Book> createBookFallback(Book book, Throwable exception) {
        log.warn(String.format("While calling createBook({%s}), experienced error: %s", book.toString(), exception.getMessage()), exception);
        return ResponseEntity.ok(new Book());
    }
}
