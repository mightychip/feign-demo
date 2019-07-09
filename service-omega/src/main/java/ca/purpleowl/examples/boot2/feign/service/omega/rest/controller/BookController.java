package ca.purpleowl.examples.boot2.feign.service.omega.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceAlphaClient;
import ca.purpleowl.examples.boot2.feign.service.omega.service.StabilizedServiceAlpha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookController {
    private final StabilizedServiceAlpha serviceAlpha;

    @Autowired
    public BookController(StabilizedServiceAlpha serviceAlpha) {
        this.serviceAlpha = serviceAlpha;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> listBooks(@RequestParam(name = "authorName", required = false) String authorName) {
        return ResponseEntity.ok(serviceAlpha.getBooksByAuthor(authorName));
    }

    @GetMapping(path = "/{bookId}", produces = "application/json")
    public ResponseEntity<Book> getById(@PathVariable("bookId") Long bookId) {
        Book book = serviceAlpha.getBookById(bookId);
        return book.getId() != null ? ResponseEntity.ok(book) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book response = serviceAlpha.createBook(book);
        return response.getId() != null ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
