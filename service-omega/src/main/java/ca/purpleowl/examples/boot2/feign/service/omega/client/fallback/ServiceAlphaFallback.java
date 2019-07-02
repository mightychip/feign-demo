package ca.purpleowl.examples.boot2.feign.service.omega.client.fallback;

import ca.purpleowl.examples.boot2.feign.service.alpha.model.Book;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceAlphaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class ServiceAlphaFallback implements ServiceAlphaClient {
    @Override
    public ResponseEntity<List<Book>> getBooksByAuthor(String authorName) {
        log.warn("Executing fallback: getBooksByAuthor");
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Book> getBookById(Long bookId) {
        log.warn("Executing fallback: getBookById");
        return ResponseEntity.ok(new Book());
    }

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        log.warn("Executing fallback: createBook");
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(book);
    }
}
