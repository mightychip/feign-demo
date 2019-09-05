package ca.purpleowl.examples.boot2.feign.service.alpha.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.client.ServiceAlpha;
import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import ca.purpleowl.examples.boot2.feign.service.alpha.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Here, we implement the {@link ServiceAlpha} interface to provide a full ReST Controller.
 */
@RestController
public class BookController implements ServiceAlpha {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public ResponseEntity<List<Book>> getBooksByAuthor(String authorName) {
        if(StringUtils.isEmpty(authorName)) {
            return ResponseEntity.ok(bookService.getAllBooks());
        } else {
            return ResponseEntity.ok(bookService.getAllBooksByAuthor(authorName));
        }
    }

    public ResponseEntity<Book> getBookById(Long bookId) {
        return ResponseEntity.of(bookService.getBookById(bookId));
    }

    public ResponseEntity<Book> createBook(Book book) {
        Long id = bookService.createBook(book);

        if(id != null) {
            book.setId(id);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
