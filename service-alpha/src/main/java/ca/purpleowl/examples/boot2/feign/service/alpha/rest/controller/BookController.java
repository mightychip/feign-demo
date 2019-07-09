package ca.purpleowl.examples.boot2.feign.service.alpha.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import ca.purpleowl.examples.boot2.feign.service.alpha.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(name = "authorName", required = false) String authorName) {
        if(StringUtils.isEmpty(authorName)) {
            return ResponseEntity.ok(bookService.getAllBooks());
        } else {
            return ResponseEntity.ok(bookService.getAllBooksByAuthor(authorName));
        }
    }

    @GetMapping(path = "/{bookId}", produces = "application/json")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.of(bookService.getBookById(bookId));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Long id = bookService.createBook(book);

        if(id != null) {
            book.setId(id);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
