package ca.purpleowl.examples.boot2.feign.service.omega.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.alpha.model.Book;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceAlphaClient;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ServiceAlphaClient serviceAlpha;

    @Autowired
    public BookController(ServiceAlphaClient serviceAlpha) {
        this.serviceAlpha = serviceAlpha;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> listBooks(@RequestParam(name = "authorName", required = false) String authorName) {
        return serviceAlpha.getBooksByAuthor(authorName);
    }

    @GetMapping(path = "/{bookId}", produces = "application/json")
    public ResponseEntity<Book> getById(@PathVariable("bookId") Long bookId) {
        return serviceAlpha.getBookById(bookId);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return serviceAlpha.createBook(book);
    }
}
