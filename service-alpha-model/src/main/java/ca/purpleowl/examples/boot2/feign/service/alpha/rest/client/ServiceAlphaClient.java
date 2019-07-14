package ca.purpleowl.examples.boot2.feign.service.alpha.rest.client;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ServiceAlphaClient {
    @GetMapping(path = "/books", produces = "application/json")
    ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam(name = "authorName", required = false) String authorName);

    @GetMapping(path = "/books/{bookId}", produces = "application/json")
    ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId);

    @PostMapping(path = "/books", produces = "application/json", consumes = "application/json")
    ResponseEntity<Book> createBook(@RequestBody Book book);
}
